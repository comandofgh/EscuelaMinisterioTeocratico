package util;

import android.text.Editable;
import android.text.TextWatcher;


public class TextChangedListener implements TextWatcher{
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public static String convertirAmayuscula(Editable nombre){
        String stg = "";
        char c;
        int aux = 0;

        nombre.toString().trim();

        for (int i=0; i<nombre.length(); i++){
            c = nombre.charAt(i);

            if (i == 0) {
                c = Character.toUpperCase(c);
            }
            if (i>=1){
                c = Character.toLowerCase(c);
            }
            if (c==' '){
                aux = (i+1);
            }
            if (aux==i){
                c = Character.toUpperCase(c);
            }
            stg+=c;
        }
        return stg;

    }

}
