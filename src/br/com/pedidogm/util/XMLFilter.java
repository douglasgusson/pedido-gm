
package br.com.pedidogm.util;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Douglas Gusson
 */
public class XMLFilter extends FileFilter {
 
    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        
        String extension = ExtensionUtils.getExtension(f);
        
        if (extension != null) {
            return extension.equals(ExtensionUtils.XML);
        }
 
        return false;
    }

    @Override
    public String getDescription() {
        return "Somente arquivos XML";
    }
}
