package net.cdahmedeh.murale.service;

import net.cdahmedeh.murale.app.AppContext;
import net.cdahmedeh.murale.event.ProviderUpdatedEvent;
import net.cdahmedeh.muralelib.provider.Provider;
import net.cdahmedeh.muralelib.domain.Configuration;
import net.cdahmedeh.muralelib.error.ConfigurationErrorException;
import org.ini4j.Profile.Section;
import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by cdahmedeh on 1/30/2017.
 */
public class ConfigurationService {
    private static final String PROVIDER_CONFIG_LOCATION = System.getProperty("user.home") + "/.murale/providers/";
    private static final String CONFIG_LOCATION = System.getProperty("user.home") + "/.murale/";
    private static final String CONFIG_FILENAME = "config.ini";

    public static List<Provider> loadProviders() {
        List<Provider> providers = new ArrayList<>();

        try {
            File providerConfigDir = new File(PROVIDER_CONFIG_LOCATION);

            for (File configFile: providerConfigDir.listFiles()) {
                Map<String, String> configMap = new HashMap<>();

                Wini ini = new Wini(configFile);

                Section configSection = ini.get("config");

                for (Entry<String, String> entry : configSection.entrySet()) {
                    configMap.put(entry.getKey(), entry.getValue());
                }

                String providerClass = configSection.get("class");
                Class<?> clazz = Class.forName(providerClass);
                Constructor<?> constructor = clazz.getConstructor();
                Provider provider = (Provider)constructor.newInstance();

                provider.loadConfiguration(configMap);

                providers.add(provider);
            }
        } catch (IOException | ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new ConfigurationErrorException("Unable to read provider configuration files", e);
        }

        return providers;
    }

    public static void saveProvider(Provider provider) {
        try {
            String providerFileName = provider.getUuid() + ".ini";

            File providerConfigDir = new File(PROVIDER_CONFIG_LOCATION);
            providerConfigDir.mkdirs();

            File providerConfigFile = new File(PROVIDER_CONFIG_LOCATION + providerFileName);
            if (providerConfigFile.exists() == false) {
                providerConfigFile.createNewFile();
            }

            Wini ini = new Wini(providerConfigFile);

            for (Entry<String, String> entry : provider.getConfiguration().entrySet()) {
                ini.put("config", entry.getKey(), entry.getValue());
            }

            ini.store();

            AppContext.getEventBus().post(new ProviderUpdatedEvent());
        } catch (IOException e) {
            throw new ConfigurationErrorException("Unable to save provider configuration file", e);
        }
    }

    public static void deleteProvider(Provider provider) {
        String providerFileName = provider.getUuid() + ".ini";

        File providerConfigDir = new File(PROVIDER_CONFIG_LOCATION);
        providerConfigDir.mkdirs();

        File providerConfigFile = new File(PROVIDER_CONFIG_LOCATION + providerFileName);
        providerConfigFile.delete();

        AppContext.getEventBus().post(new ProviderUpdatedEvent());
    }

    public static Configuration loadUserConfiguration() {
        Configuration configuration = null;

        try {
                File configDir = new File(CONFIG_LOCATION);
                configDir.mkdirs();

                File configFile = new File(CONFIG_LOCATION + CONFIG_FILENAME);

                if (configFile.exists() == false) {
                    saveConfiguration(new Configuration());
                }

                Map<String, String> configMap = new HashMap<>();

                Wini ini = new Wini(configFile);

                configuration = new Configuration();

                configuration.setWaitTime(Integer.parseInt(ini.get("config", "waittime")));
        } catch (IOException e) {
            throw new ConfigurationErrorException("Unable to read configuration file", e);
        }

        return configuration;
    }

    public static void saveConfiguration(Configuration configuration) {
        try {
            File configDir = new File(CONFIG_LOCATION);
            configDir.mkdirs();

            File configFile = new File(CONFIG_LOCATION + CONFIG_FILENAME);
            if (configFile.exists() == false) {
                configFile.createNewFile();
            }

            Wini ini = new Wini(configFile);

            ini.put("config", "waittime", configuration.getWaitTime());

            ini.store();
        } catch (IOException e) {
            throw new ConfigurationErrorException("Unable to save provider configuration file", e);
        }
    }
}
