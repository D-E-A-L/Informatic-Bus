package com.example.lectortarjetasbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class lector extends Activity implements OnClickListener {
	
	String direcIP;
	Button btnEnviar;
	EditText edtxtCi;
	TextView txtBuscando;
	String ci;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lectorvista);
		
		btnEnviar =(Button) findViewById(R.id.buttonCIenviar);
		edtxtCi=(EditText) findViewById(R.id.editTextCImanual);
		txtBuscando=(TextView) findViewById(R.id.textViewQR);
		
		btnEnviar.setOnClickListener(this);
		
		Bundle bolsarecibida= getIntent().getExtras();
		direcIP = bolsarecibida.getString("ipDirec");
	    
		
		
		
		
		
	}
	
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==btnEnviar){
			ci= edtxtCi.getText().toString();
			
			Intent intento1= new Intent(lector.this, Descuento.class);
			Bundle bolsa = new Bundle();
			bolsa.putString("ci", ci);
			intento1.putExtras(bolsa);
			
			startActivity(intento1);
			
		}
		
	}

}
