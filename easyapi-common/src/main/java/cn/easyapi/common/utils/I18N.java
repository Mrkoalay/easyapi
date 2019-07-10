package cn.easyapi.common.utils;

import org.springframework.context.i18n.LocaleContextHolder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

/**
 * 国际化工具类
 *
 * @author lch2
 * @since 2018/12/19
 */
public class I18N {

    private I18N() {
    }

    private static final Control utf8Control = new UTF8Control();
    public static String getMessage(String key) {
        try {
            Locale.setDefault(LocaleContextHolder.getLocale());
            return ResourceBundle.getBundle("i18n/messages", utf8Control).getString(key);
        } catch (MissingResourceException e) {
            return key;
        }
    }

    public static String getMessage(String key, Object... params) {
        return MessageFormat.format(getMessage(key), params);
    }

    private static class UTF8Control extends Control {
        @Override
        public ResourceBundle newBundle
                (String baseName, Locale locale, String format, ClassLoader loader, boolean reload)
                throws IllegalAccessException, InstantiationException, IOException
        {
            // The below is a copy of the default implementation.
            String bundleName = toBundleName(baseName, locale);
            String resourceName = toResourceName(bundleName, "properties");
            ResourceBundle bundle = null;
            InputStream stream = null;
            if (reload) {
                URL url = loader.getResource(resourceName);
                if (url != null) {
                    URLConnection connection = url.openConnection();
                    if (connection != null) {
                        connection.setUseCaches(false);
                        stream = connection.getInputStream();
                    }
                }
            } else {
                stream = loader.getResourceAsStream(resourceName);
            }
            if (stream != null) {
                try {
                    // Only this line is changed to make it to read properties files as UTF-8.
                    bundle = new PropertyResourceBundle(new InputStreamReader(stream, "UTF-8"));
                } finally {
                    stream.close();
                }
            }
            return bundle;
        }
    }
}
