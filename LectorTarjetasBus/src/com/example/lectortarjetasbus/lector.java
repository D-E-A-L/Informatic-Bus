package com.example.lectortarjetasbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class lector extends Activity implements OnClickListener {
	
	String direcIP;
	Button btnEnviar, btnScan;
	EditText edtxtCi;
	TextView txtBuscando;
	String ci, resultado;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lectorvista);
		
		btnEnviar =(Button) findViewById(R.id.buttonCIenviar);
		edtxtCi=(EditText) findViewById(R.id.editTextCImanual);
		txtBuscando=(TextView) findViewById(R.id.textViewQR);
		btnScan=(Button) findViewById(R.id.buttonScan);
		btnEnviar.setOnClickListener(this);
		btnScan.setOnClickListener(this);
		
		Bundle bolsarecibida= getIntent().getExtras();
		direcIP = bolsarecibida.getString("ipDirec");
	    
		
		
		
		
		
	}
	
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(v==btnEnviar){
			ci= edtxtCi.getText().toString();
			
			Intent intento1= new Intent(lector.this, Client.class);
			Bundle bolsa = new Bundle();
			bolsa.putString("ci", ci);
			bolsa.putString("ip", direcIP);
			intento1.putExtras(bolsa);
			
			startActivity(intento1);
			
		}
		
		if(v==btnScan){
			IntentIntegrator.initiateScan(lector.this);
			
		}
		
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case IntentIntegrator.REQUEST_CODE:
            {
                if (resultCode == RESULT_CANCELED){
                }
                else
                {
                    //Recogemos los datos   que nos envio el código Qr/codigo de barras
                    IntentResult scanResult = IntentIntegrator.parseActivityResult(
                            requestCode, resultCode, data);
                    String UPCScanned = scanResult.getContents();
                    resultado=UPCScanned;
                    txtBuscando.setText(resultado);
                    edtxtCi.setText(resultado);
                    //cOMO ES SOLO UN EJEMPLO LO SACAREMOS POR PANTALLA.
                    Toast.makeText(getApplicationContext(),UPCScanned,Toast.LENGTH_LONG
                    ).show();
                }
                break;
            }
        }
    }

}
