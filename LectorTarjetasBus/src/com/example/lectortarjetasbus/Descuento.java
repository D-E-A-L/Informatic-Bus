package com.example.lectortarjetasbus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Descuento extends Activity implements OnClickListener {
	
	TextView txtCi;
	String ci;
	Button btDescontar;
	Button btnExit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.descuentovista);
		
		txtCi = (TextView) findViewById(R.id.textViewCI);
		btDescontar =(Button) findViewById(R.id.buttonDescontar);
		
		Bundle bolsarecibida= getIntent().getExtras();
		ci = bolsarecibida.getString("ci");
		txtCi.setText(ci);
		
		
	}
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==btDescontar){
			//hacer consulta
			//descontar
			
		}
	}

}
