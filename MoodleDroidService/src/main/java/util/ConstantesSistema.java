package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Classe com as constantes gerais do Sistema.
 * 
 * @author Felipe
 * @date 08/09/2013
 * 
 */
public class ConstantesSistema {
	
	private static final String CONFIG_FILE = "META-INF/config.properties";
	private static Properties properties = new Properties();
	static{
		try{
			InputStream inputStream = ConstantesSistema.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
			properties.load(inputStream);
		}catch (IOException e) {
			//TODO: tratar exceção
			e.printStackTrace();
		}
	}
	
	/**
	 * @author Felipe
	 * @return {@link String}
	 *  
	 *  Retorna o código vindo do config.php. Deve ser somado à senha do usuário para fazer o login.
	 */
	private static String iniciaCodePassWord() {
		String codigo = "";
		File diretorio = new File(MOODLE_URL);
		File[] arquivos = diretorio.listFiles();
		File arquivoConfig = null;
		for (int i = 0; i < arquivos.length; i++) {
			if (arquivos[i].getName().equals("config.php")) {
				arquivoConfig = arquivos[i];
				break;
			}
		}
		try {
			FileReader fr = new FileReader(arquivoConfig);
			BufferedReader br = new BufferedReader(fr);
			String strLinha;
			while (br.ready()) {
				strLinha = br.readLine();
				if (strLinha.contains("passwordsaltmain")) {
					int beginIndex = strLinha.indexOf("'");
					int endIndex = strLinha.lastIndexOf("'");
					codigo = strLinha.substring(beginIndex + 1, endIndex);
					break;
				}
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return codigo;
	}
	
	/**
	 * Diretorio onde o moodle está instalado no sistema de arquivos.
	 * @author Felipe
	 */
	public static final String MOODLE_URL = properties.getProperty("diretorio_moodle");
	
	/**
	 * Codigo vindo do config.php. Deve ser somado a senha do usuário para fazer o login.
	 * @author Felipe
	 */
	public static final String CODE_PASSWORD = iniciaCodePassWord();
	
	/**
	 * URL da conexão com o banco de dados do moodle.
	 * @author Felipe
	 */
	public static final String CONNECTION_URL = properties.getProperty("url");
	
	/**
	 * Usuário do banco de dados do moodle.
	 * @author Felipe
	 */
	public static final String USER = properties.getProperty("user");
	
	/**
	 * Senha do banco de dados do moodle.
	 * @author Felipe
	 */
	public static final String PASSWORD = properties.getProperty("password");

}
