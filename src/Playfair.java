
public class Playfair extends Cryptage {

	public Playfair() {
		this("playfair");
	}

	public Playfair(String clef) {
		super(clef);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("\nMatrice de cryptage :");
		for (int i = 0; i < 6; i ++) {
			for (int j = 0; j < 6; j ++)
				sb.append(' ').append(alphabetDeCryptage.charAt(i * 6 + j));
			if (i < 5)
				sb.append("\n                     ");
		}
		return super.toString() + sb.toString();
	}
	
	private String transforme(String texte, boolean crypte) {
		// crypte est true si cryptage, false si d�cryptage
		StringBuffer sb = new StringBuffer(texte);
		for (int k = 0; k < sb.length() - 1; k += 2) {
			char c1 = sb.charAt(k);
			char c2 = sb.charAt(k + 1);
			int iAlpha1 = alphabetDeCryptage.indexOf(c1);
			int iAlpha2 = alphabetDeCryptage.indexOf(c2);
			if (iAlpha1 == -1 || iAlpha2 == -1)
				continue;
			int i1 = iAlpha1 / 6; int i2 = iAlpha2 / 6;
			int j1 = iAlpha1 % 6; int j2 = iAlpha2 % 6;
			if (i1 == i2) { // m�me ligne
				int nj1 = (j1 + (crypte ? 1 : 5)) % 6;
				int nj2 = (j2 + (crypte ? 1 : 5)) % 6;
				sb.setCharAt(k, alphabetDeCryptage.charAt(i1 * 6 + nj1));
				sb.setCharAt(k + 1, alphabetDeCryptage.charAt(i2 * 6 + nj2));
			} else
				if (j1 == j2) { // m�me colonne
					int ni1 = (i1 + (crypte ? 1 : 5)) % 6;
					int ni2 = (i2 + (crypte ? 1 : 5)) % 6;
					sb.setCharAt(k, alphabetDeCryptage.charAt(ni1 * 6 + j1));
					sb.setCharAt(k + 1, alphabetDeCryptage.charAt(ni2 * 6 + j2));					
				} else { // cas du rectangle
					sb.setCharAt(k, alphabetDeCryptage.charAt(i1 * 6 + j2));
					sb.setCharAt(k + 1, alphabetDeCryptage.charAt(i2 * 6 + j1));
				}
		}
		return sb.toString();
	}
	
	public String cryptage(String s) {
		return transforme(s, true);
	}

	public String deCryptage(String s) {
		return transforme(s, false);
	}

	public static void main(String[] args) {
		Playfair p = new Playfair();
		System.out.println(p);
		String texte = "le langage java";
		System.out.println(p.cryptage(texte));
		System.out.println(p.deCryptage(p.cryptage(texte)));
	}

}
