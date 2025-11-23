package equipe;
import java.util.Random;

public class Elu extends Employe {
	private Random rand = new Random();
    float min = 0f;     // 10 000 €
    float max = 500000f;    // 500 000 €
	
	public Elu(String Nom, String Prenom, int Age) {
		super(Nom, Prenom, Age);
	}
	
	public float EvaluerBenefice(Projet p) {
		float benefice = min + rand.nextFloat() * (max - min);
		p.setBenefice(benefice);
		return benefice;
	}
}
