
public class Decalage extends Cryptage {

	public Decalage() {
		this("decalage");
	}

	public Decalage(String clef) {
		super(clef);
		int pivot = ALPHABET.length() - clef.length();
		alphabetDeCryptage = alphabetDeCryptage.substring(pivot) + alphabetDeCryptage.substring(0, pivot);
	}
	
	public String toString() {
		return super.toString() + "\nTransformation de cryptage : " + ALPHABET
				                + "\n                             " + alphabetDeCryptage;
 	}

	private static String transforme(String texte, String alphaSource, String alphaCible) {
		StringBuffer sb = new StringBuffer(texte);
		for (int i = 0; i < sb.length(); i ++) {
			int j = alphaSource.indexOf(sb.charAt(i));
			if (j != -1)
				sb.setCharAt(i, alphaCible.charAt(j));
		}
		return sb.toString();
	}
	
	public String cryptage(String s) {
		return transforme(s, ALPHABET, alphabetDeCryptage);
	}

	public String deCryptage(String s) {
		return transforme(s, alphabetDeCryptage, ALPHABET);
	}

	public static void main(String[] args) {
		Decalage d = new Decalage();
		System.out.println(d);
		String texte = "le langage java";
		System.out.println(d.cryptage(texte));
		System.out.println(d.deCryptage(d.cryptage(texte)));
	}

}
