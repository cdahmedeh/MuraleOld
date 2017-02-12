package net.cdahmedeh.murale.ui.dialog;

import com.alee.laf.button.WebButton;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.rootpane.WebDialog;
import lombok.Getter;
import lombok.Setter;
import net.cdahmedeh.murale.icon.Icons;
import net.cdahmedeh.murale.provider.Provider;
import net.cdahmedeh.murale.service.ConfigurationService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Template for Provider Setting Editors
 *
 * Created by cdahmedeh on 2/12/2017.
 */
public abstract class ProviderDialog<P extends Provider> extends WebDialog {
    @Getter
    @Setter
    protected P provider;

    public ProviderDialog(P p) {
        if (provider == null) {
            setProvider(loadNewProvider());
        } else {
            setProvider(p);
        }

        setBounds(new Rectangle(640, 480));
        setLocationRelativeTo(null);
        setModalityType(ModalityType.APPLICATION_MODAL);

        setLayout(new BorderLayout());

        WebPanel formPanel = getFormPanel();
        formPanel.setUndecorated(false);
        formPanel.setMargin(5);
        formPanel.setRound(5);
        add(formPanel, BorderLayout.CENTER);

        add(new ButtonPanel(), BorderLayout. SOUTH);

        loadProvider(provider);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public abstract WebPanel getFormPanel();

    public abstract void loadProvider(P provider);

    protected abstract void readProvider();

    public abstract P loadNewProvider();

    private class ButtonPanel extends WebPanel {
        public ButtonPanel() {
            setLayout(new BoxLayout(ButtonPanel.this, BoxLayout.X_AXIS));

            add(Box.createHorizontalGlue());

            WebButton cancelButton = new WebButton("Cancel", Icons.getIcon("cancel"));
            cancelButton.setMargin(5);
            add(cancelButton);

            cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    dispose();
                }
            });

            WebButton okButton = new WebButton("Ok", Icons.getIcon("save"));
            okButton.setMargin(5);
            add(okButton);

            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    readProvider();
                    ConfigurationService.saveProvider(provider);

                    setVisible(false);
                    dispose();
                }
            });
        }
    }
}
