//Klasse for representasjon av noder i en regneklynge

class Node {
  // oppretter instansvariabler av type int for minne og prosessorer
  private int minne;
  private int antPros;

  // Konstruktor som tar imot minne og antall prosessorer noden skal ha.
  // Denne kalles automatisk naar objektet opprettes.
  public Node (int minne, int antPros) {
    this.minne = minne;
    this.antPros = antPros;
  }

  // henter antall prosesserer for en enkelt node
  // returverdi av type int
  public int antProsessorer() {
    return antPros;
  }

  // basert paa en gitt minnestorrelse, svarer denne metoden paa om noden har tilstrekkelig (storre eller lik) minne.
  // returverdi av type boolean.
  public boolean nokMinne (int paakrevdMinne) {
    boolean sann = true;
    boolean usann = false;
    if (paakrevdMinne <= minne) {
      return sann;
    }
    else {
      return usann;
    }
  }
}
