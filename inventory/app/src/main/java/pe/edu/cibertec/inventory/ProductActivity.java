package pe.edu.cibertec.inventory;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class ProductActivity extends AppCompatActivity {

    TextInputEditText etName, etDescription, etQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);
        etQuantity = findViewById(R.id.etQuantity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.product_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String name = etName.getText().toString();
        String description = etDescription.getText().toString();
        int quantity = Integer.parseInt(etQuantity.getText().toString());

        Product product = new Product(name,description,quantity);

        Intent intent = getIntent();
        //anadir info al intent (nombre (identificador) y valor)
        intent.putExtra("product_name",name);
        intent.putExtra("product_description",description);
        intent.putExtra("product_cantidad",quantity);

        //Confirmamos que el resultado exitodo (OK) y le pasamos la info q viaja (intent)
        //2.
        //TAREA: En lugar de pasar 3 parametros pasar toda toda la Clase
        //TIP : Google pase class Inent
        //Gson -> Convierte un objeto en Strings
        setResult(RESULT_OK, intent);

        //cierra la pantalla
        finish();
        return true;
    }
}
