
class Hovedprogram {
  public static void main(String[] args) {
    // oppretter regneklyngen abel og sender navnet paa filen som argument.
    Regneklynge abel = new Regneklynge("regneklynge3.txt");


    System.out.println("Noder med minst 32 GB: " + abel.noderMedNokMinne(32));
    System.out.println("Noder med minst 64 GB: " + abel.noderMedNokMinne(64));
    System.out.println("Noder med minst 128 GB: " + abel.noderMedNokMinne(128));
    System.out.println();
    System.out.println("Antall Prosessorer: " + abel.antallProsessorer());
    System.out.println("Antall Rack: " + abel.antallRacks());

  }
}
