package eduardocruzproductions.myclothesstock;

import android.app.AlertDialog;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

import eduardocruzproductions.myclothesstock.adaptadores.ClienteAdapterListView;
import eduardocruzproductions.myclothesstock.adaptadores.GradeAdapterListView;
import eduardocruzproductions.myclothesstock.adaptadores.ProdutoAdapterListView;
import eduardocruzproductions.myclothesstock.entidades.Cliente;
import eduardocruzproductions.myclothesstock.entidades.Grade;
import eduardocruzproductions.myclothesstock.entidades.ItensVenda;
import eduardocruzproductions.myclothesstock.entidades.Produto;
import eduardocruzproductions.myclothesstock.util.ValidadorCPF;

public class Venda extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private static Cliente cliente;
    private static List<ItensVenda> itensVenda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venda);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_venda, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private ListView clienteListView;
        private TextView clienteTextViewNome;
        private TextView clienteTextViewCpf;

        private ListView produtoListView;
        private Button produtoBtn;


        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView;

            switch (getArguments().getInt(ARG_SECTION_NUMBER)){

                case 1:

                    //secao cliente

                    rootView = inflater.inflate(R.layout.fragment_venda_cliente, container, false);

                    List<Cliente> listAll = Cliente.listAll(Cliente.class);

                    clienteTextViewNome = (TextView) rootView.findViewById(R.id.venda_cliente_textView_nome);
                    clienteTextViewCpf = (TextView) rootView.findViewById(R.id.venda_cliente_textView_cpf);

                    clienteTextViewNome.setText("");
                    clienteTextViewCpf.setText("");

                    final ClienteAdapterListView adapter = new ClienteAdapterListView(getContext(),listAll);
                    clienteListView = (ListView) rootView.findViewById(R.id.venda_cliente_listView);
                    clienteListView.setAdapter(adapter);
                    clienteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            cliente = adapter.getItem(i);
                            clienteTextViewNome.setText(cliente.getNome());
                            clienteTextViewCpf.setText(ValidadorCPF.imprimeCPF(cliente.getCpf()));

                        }
                    });

                    return rootView;

                case 2:

                    //Secao Produto

                    rootView = inflater.inflate(R.layout.fragment_venda_produto, container, false);

                    produtoListView = (ListView) rootView.findViewById(R.id.venda_produto_listView);
                    produtoBtn = (Button) rootView.findViewById(R.id.venda_produto_btn_adicionar);

                    produtoBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                            View promptView = layoutInflater.inflate(R.layout.fragment_venda_produto_select_produto, null);
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                            alertDialogBuilder.setView(promptView);

                            final EditText editTextBuscar = (EditText) promptView.findViewById(R.id.venda_produto_alert_selectProduto_editText_buscar);
                            final ListView listView = (ListView) promptView.findViewById(R.id.venda_produto_alert_selectProduto_listView);

                            final ProdutoAdapterListView adapterListViewProduto = new ProdutoAdapterListView(getContext(), Produto.listAll(Produto.class));

                            listView.setAdapter(adapterListViewProduto);

                            editTextBuscar.addTextChangedListener(new TextWatcher() {

                                    @Override
                                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                        List<Produto> list = Produto.find(Produto.class, "REFERENCIA like('%"+charSequence+"%')");
                                        adapterListViewProduto.updateItens(list);
                                        listView.setAdapter(adapterListViewProduto);

                                    }

                                    @Override
                                    public void afterTextChanged(Editable editable) {

                                    }

                            });

                            //action created by click on listView
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                    LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
                                    View promptView = layoutInflater.inflate(R.layout.fragment_venda_produto_select_grade, null);
                                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                                    alertDialogBuilder.setView(promptView);

                                    final ListView listView = (ListView) promptView.findViewById(R.id.venda_produto_alert_selectGrade_listView);

                                    Produto produto = adapterListViewProduto.getItem(i);

                                    List<Grade> listGrade = Grade.find(Grade.class,"PRODUTO = ?", produto.getId().toString());

                                    GradeAdapterListView adapterListViewGrade = new GradeAdapterListView(getContext(),listGrade);
                                    listView.setAdapter(adapterListViewGrade);

                                    AlertDialog alert = alertDialogBuilder.create();
                                    alert.show();



                                }
                            });

                            AlertDialog alert = alertDialogBuilder.create();
                            alert.show();

                        }
                    });

                    return rootView;

                case 3:

                    //Secao Pagamento

                    rootView = inflater.inflate(R.layout.fragment_venda_pagamento, container, false);
                    return rootView;

                default:

                    rootView = inflater.inflate(R.layout.fragment_venda_cliente, container, false);
                    return rootView;

            }
        }

    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Cliente";
                case 1:
                    return "Produtos";
                case 2:
                    return "Pagamento";
            }
            return null;
        }
    }
}
