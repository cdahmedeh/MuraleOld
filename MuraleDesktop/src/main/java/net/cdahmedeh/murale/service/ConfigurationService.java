package net.cdahmedeh.murale.service;

import net.cdahmedeh.murale.domain.Provider;
import net.cdahmedeh.murale.domain.UserConfiguration;
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
    private static final String PROVIDER_CONFIG_LOCATION = "C:/Users/cdahmedeh/.murale/providers/";

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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return providers;
    }

    public static void saveProvider(Provider provider) {
        try {
            String providerFileName = provider.getUuid() + ".ini";

            File providerConfigDir = new File(PROVIDER_CONFIG_LOCATION);
            providerConfigDir.mkdirs();

            File providerConfigFile = new File(PROVIDER_CONFIG_LOCATION + providerFileName);
            providerConfigFile.createNewFile();

            Wini ini = new Wini(providerConfigFile);

            for (Entry<String, String> entry : provider.getConfiguration().entrySet()) {
                ini.put("config", entry.getKey(), entry.getValue());
            }

            ini.store();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static UserConfiguration loadUserConfiguration() {
        return null;
    }
}
