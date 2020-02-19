// klasse som representerer Racks i en regneklynge
import java.util.ArrayList;

class Rack {
  // oppretter en Arraylist som skal holde paa antall noder.
  // lengden av denne er av en storrelse som vil bli oppgitt. Derfor kunne det
  // vaert en vanlig Array ogsaa, men det er ikke nodvendig. Jeg synes det er mer
  // kronglete aa haandtere et vanlig Array siden storrelsen er den samme hele tiden,
  // mens ArrayLists har size()-funksjonen som er veldig gjev.
  private ArrayList<Node> nodeListe;
  private int maksAntNoder;

  // Konstruktor som tar imot hvor mange noder et rack skal inneholde.
  public Rack (int maksAntNoder) {
    this.maksAntNoder = maksAntNoder;
    nodeListe = new ArrayList<Node>();
  }

  // Denne metoden tar imot en node og sjekker om det er plass i racket (nodeListe).
  // dersom det er det legger den til noden i listen og returnerer true. Hvis ikke returnerer den false
  // returverdi av type boolean.
  public boolean settInn(Node node) {
    boolean sann = true;
    boolean usann = false;
    if (nodeListe.size() < maksAntNoder) {
      nodeListe.add(node);
      return sann;
    }
    else {
      return usann;
    }
  }

  // henter antall noder i racket.
  // returverdi av type int.
  public int getAntNoder() {
    return nodeListe.size();
  }

  // henter totalt antall prosessorer i racket.
  // returverdi av type int
  public int antPros() {
    int teller = 0;
    for (int i = 0; i < nodeListe.size(); i++) {
      teller += nodeListe.get(i).antProsessorer();
    }
    return teller;
  }

  // henter antall noder som har minimum en gitt minnestorrelse i racket.
  // returverdi av type int
  public int noderMedNokMinne(int paakrevdMinne) {
    int antall = 0;
    for (int i = 0; i < nodeListe.size(); i++) {
      if (nodeListe.get(i).nokMinne(paakrevdMinne)) {
        antall++;
      }
    }
    return antall;
  }
}
