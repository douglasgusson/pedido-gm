package br.com.pedidogm.domain;

/**
 *
 * @author douglas
 */
public class Info {

    private static final String NAME_SYSTEM = "PedidoGM";
    private static final String VERSION = "v0.9.3";
    private static StringBuffer buffer;

    public static String getVersion() {
        return VERSION;
    }

    public static String getNameSystem() {
        return NAME_SYSTEM;
    }

    public static String getNameMoreVersion() {
        return new StringBuilder(NAME_SYSTEM)
                .append(" ").append(VERSION).toString();
    }

    public static String getSystemProperty() {
        buffer = new StringBuffer();
        initProperty("Fornecedor Java", "java.vendor");
        initProperty("URL Fornecedor Java", "java.vendor.url");
        initProperty("Versão Java", "java.version");
        initProperty("Nome Sistema Operacional", "os.name");
        initProperty("Versão Sistema Operacional", "os.version");
        initProperty("Arquitetura Sistema Operacional", "os.arch");
        initProperty("Diretório Programa", "user.dir");
        initProperty("Nome Usuário", "user.name");
        initProperty("Fuso Horário", "user.timezone");
        return buffer.toString();
    }

    private static String initProperty(String description, String propertyStr) {
        if (buffer == null) {
            buffer = new StringBuffer();
        }
        buffer.append(description).append(": ");
        buffer.append(System.getProperty(propertyStr)).append("\n");
        return buffer.toString();
    }

}
