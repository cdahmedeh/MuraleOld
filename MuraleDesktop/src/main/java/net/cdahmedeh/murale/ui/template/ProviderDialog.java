package net.cdahmedeh.murale.ui.template;

import net.cdahmedeh.murale.domain.Provider;

/**
 * Created by cdahmedeh on 2/5/2017.
 */
public interface ProviderDialog<P extends Provider> {
    public void loadProvider(P provider);

    public P readProvider(P provider);
}
