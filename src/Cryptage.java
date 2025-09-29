import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public abstract class Cryptage {
	final static String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";
	protected String alphabetDeCryptage;
	protected String clef;
	
	public Cryptage(){
		this("clef");
	}
	
	public Cryptage(String clef) {
//		valideClef(clef);
		this.clef = clef;
		StringBuffer sb = new StringBuffer(clefEpuree()).append(ALPHABET);
		for (int i = 0; sb.length() > ALPHABET.length(); i ++) {
			String caractere = sb.substring(i, i +1);
			for (int j = sb.indexOf(caractere, i + 1); j > -1; j = sb.indexOf(caractere, i + 1))
					sb.deleteCharAt(j);
		}
		alphabetDeCryptage = sb.toString();
	}
	
	private String clefEpuree() {
		// retourne la clef �pur�e, sans les caract�res absents de l'alphabet
		StringBuffer sb = new StringBuffer(clef);
		for (int i = 0; i < sb.length(); i ++)	
			if (ALPHABET.indexOf(sb.charAt(i)) == -1) {
				sb.deleteCharAt(i);
				i --;
			}
		return sb.toString();
	}
	
//	private void valideClef(String clef) {
//      // utiliser si on ne souhaite pas �purer la clef	
//		if (clef == null)
//			throw new IllegalArgumentException("Clef invalide : " + clef);
//		for (int i = 0; i < clef.length(); i ++)
//			if (ALPHABET.indexOf(clef.charAt(i)) == -1)
//				throw new IllegalArgumentException("Clef invalide (caract�res absents de l'alphabet) : " + clef);
//	}
	
	public String toString(){
		return "Cryptage " + getClass().getSimpleName() + "\nMot Clef : " + clef;
	}
	
	public abstract String cryptage(String s);
	public abstract String deCryptage(String s);	
	
	public void crypteVersFichier(String texte, String nomFichier) throws IOException {
		String texteCrypte = cryptage(texte);
		FileWriter writer = new FileWriter(nomFichier);
		writer.write(texteCrypte);
		writer.close();
	}

	public String decrypteFichier(String nomFichier) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(nomFichier));
        StringBuilder sb = new StringBuilder();
        String ligne;
        while ((ligne = reader.readLine()) != null) {
            sb.append(deCryptage(ligne));
        }
        reader.close();
        return sb.toString();
	}
	
}
