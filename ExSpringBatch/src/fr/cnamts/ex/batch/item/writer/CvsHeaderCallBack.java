package fr.cnamts.ex.batch.item.writer;

import java.io.IOException;
import java.io.Writer;

import org.springframework.batch.item.file.FlatFileHeaderCallback;

public class CvsHeaderCallBack implements FlatFileHeaderCallback {

	private String entete ="name;phone;email;country;createdAt";
	
 
	public void setEntete(String entete) {
		this.entete = entete;
	}


	@Override
	public void writeHeader(final Writer writer) throws IOException {
		//TODO writer.write pour generer l'entete
		writer.write(entete);
	}


}
