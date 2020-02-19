Oblig1
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
// importerer de nodvendige settene med funksjoner fra Java-biblioteket
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

class Regneklynge {
	// Deklarerer nyttige instansvariabler
	private String filNavn;
	private int noderPerRack;
	// Oppretter en ArrayList som vil fungere som selve regneklyngen. En liste med racks.
	// ArrayList er et bedre alternativ til en vnlig Array siden storrelsen paa
	// regneklyngen/listen med racks vil oke etterhvert som rackene blir fylt opp og nye opprettes.
	private ArrayList<Rack> listeRacks;

	// Konstruktoren tar inn et filnavn som parameter. Dette trengs til aa aksessere en gitt fil.
	public Regneklynge(String filNavn) {
		// setter instansvariabelen filNavn til vaere lik det gitte filnavnet.
		this.filNavn = filNavn;
		// Oppretter en Scanner som vi kan bruke til aa lese av filen for resten av programmet.
		Scanner fil = null;
		// Lager en try-catch test som skjekker om filen er gyldig. Hvis ikke kjøres Exception-koden.
    try {
        fil = new Scanner(new File(filNavn));
    } catch (Exception e) {
        System.out.println("Kan ikke lese " + filNavn + "!");
        System.exit(-1);
    }

		// henter ut forste tall fra filen, som representerer noderPerRack.
		noderPerRack = fil.nextInt();
		// fullforer deklrasjonen av ArrayListen her og oppretter det forste racket.
		listeRacks = new ArrayList<Rack>();
		Rack rack = new Rack(noderPerRack);
		// setter racket inn i regneklyngen (listeRacks) i slutten av listen.
		listeRacks.add(rack);

		// Her lager vi en lokke som leser hvert tall fra listen saa lenge det er flere tall aa lese.
		while (fil.hasNextInt()) {
			int antallNoder = fil.nextInt();
			int minnePerNode  = fil.nextInt();
			int antallProsessorer = fil.nextInt();

			// Kaller paa metoden settInnNode med tallene vi nettopp leste ut ifra filen.
			// Dette er en effektiv metode fordi det tillater filen til aa kunne ha mange flere linjer
			// og saa lenge det er skrevet paa samme format vil vil den fortsette a gi variablene nye
			// verdier og kalle paa settInnNode med disse verdiene.
			settInnNode(antallNoder, minnePerNode, antallProsessorer);
		}
	}

	// Denne metoden tar imot verdiene sendt fra Konstruktoren og gaar gjennom en lokke
	// basert paa "antNoder" og oppretter nye noder basert paa "minne" og "antPros"
 	public void settInnNode(int antNoder, int minne, int antPros) {
		for (int i = 0; i < antNoder; i++){
      Node node = new Node(minne, antPros);
			// videre kaller vi paa en ny metode for hver iterasjon med en ny node.
			kallSettInn(node);
    }
	}

	// Denne tar imot en node, henter et rack (det siste racket i ArrayListen),
	// ser om det er plass og hvis det er det, setter den inn noden.
	// dette gjor den ved hjelp av metoden "settInn" fra Rack som returnerer en verdi av typen boolean
	// basert paa om det er plass eller ikke.
	public void kallSettInn(Node node) {
		// det er viktig aa hente det siste racket ettersom det er her de nyeste (med ledig plass) blir plassert.
		Rack rack = listeRacks.get(listeRacks.size() - 1);
		if (rack.settInn(node)) {
		}
		// hvis det ikke er plass, oppretter den et nyttRack og setter noden inn her i stedet,
		// og setter saa racket inn i regneklyngen sist i regneklyngen.
		else {
			Rack nyttRack = new Rack(noderPerRack);
			nyttRack.settInn(node);
			listeRacks.add(nyttRack);
		}
	}

	// metode som henter totalt antall prosessorer i listen med racks (regneklyngen).
	// returverdi av type int
	public int antallProsessorer() {
		int teller = 0;
		for (int i = 0; i < listeRacks.size(); i++) {
			teller += listeRacks.get(i).antPros();
		}
		return teller;
	}

	// henter antall noder med et gitt minimum minnestorrelse.
	// returverdi av type int
	public int noderMedNokMinne(int paakrevdMinne) {
		int antall = 0;
		for (int i = 0; i < listeRacks.size(); i++) {
			antall += listeRacks.get(i).noderMedNokMinne(paakrevdMinne);
		}
		return antall;
	}

	// henter antall rack i regneklyngen
	// returverdi av type int
	public int antallRacks() {
		return listeRacks.size();
	}
}
