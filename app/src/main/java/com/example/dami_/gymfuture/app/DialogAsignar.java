package com.example.dami_.gymfuture.app;

import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

//import com.clubpreferencial.petcard.R;



/*
 DialogProducto dialogProducto = new DialogProducto();
            final ItemMovilWS itemMovilWS = new Select().from(ItemMovilWS.class).where("primaryKey = ?",novedad.getItem()).executeSingle();
            dialogProducto.item = itemMovilWS;
            dialogProducto.iAgrgegar = new DialogProducto.IAgrgegar()
            {
               @Override
               public void onConfirmar(final int nuevaCantidad)
               {

               }
            };
            dialogProducto.show(mContext.getFragmentManager(),"NoticeDialogFragment");
 */
/*
public class DialogAsignar extends DialogFragment
{

    private View rootView;
    Button buttonAgregar;
    ImageView iv_cancel;
    public static String idMascota;
    ProgressDialog progressDialog;

    public static IAgrgegar iAgrgegar;

    public interface IAgrgegar
    {
        void onConfirmar();
    }

    public DialogAsignar()
    {
    }


    @Override
    public void onStart()
    {
        super.onStart();

        if (getDialog() == null)
            return;

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        getDialog().getWindow().setLayout(width, getDialog().getWindow().getAttributes().height);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.dialog_asignar, container);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        findViews();
        setData();
        setEvents();
        return rootView;
    }

    private void findViews()
    {
        buttonAgregar = (Button)rootView.findViewById(R.id.buttonAgregar);
        iv_cancel = (ImageView)rootView.findViewById(R.id.iv_cancel);

    }

    void setData()
    {

    }

    void setEvents()
    {
        iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                dismiss();
            }
        });

        buttonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                iAgrgegar.onConfirmar();
                dismiss();
            }
        });
    }
    /*

    JSONObject getPararms()
    {
        Usuario usuario = new Select().from(Usuario.class).executeSingle();
        Map<String, String> params = new HashMap<String, String>();
        params.put(urls.param_idMascota2, idMascota);
        params.put(urls.param_idUsuario, usuario.get_Id());
        params.put(urls.param_observaciones,etObservaciones.getText().toString());
        return new JSONObject(params);
    }

    public void login(JSONObject pararms)
    {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.text_subiendo_datos));
        progressDialog.show();

        String URL = urls.url_encontreMiMascota;
        app.L(URL+" "+pararms.toString());
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jr = new JsonObjectRequest(Request.Method.PUT, URL, pararms,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                app.L(response.toString());
                progressDialog.dismiss();
                try
                {
                    Toast.makeText(getActivity(), response.getString("mensaje"), Toast.LENGTH_SHORT).show();
                    if (response.getString("codigo").equals("1"))
                    {
                        dismiss();
                        iAgrgegar.onConfirmar();
                    }
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), R.string.error_json, Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();
                progressDialog.dismiss();
                Toast.makeText(getActivity(), R.string.error_internet, Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jr);
    }
    */

//}

