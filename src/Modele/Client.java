package Modele;

public class Client {
    private String noCB;

    public void setNoCB(String noCB) {
        this.noCB = noCB;
    }

	public Client(String noCB) {
		this.noCB = noCB;
	}

	public String getNoCB() {
		return noCB;
	}
}
