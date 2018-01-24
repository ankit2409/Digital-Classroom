package com.example.dell.registrationpage;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Quizzes_Teacher.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Quizzes_Teacher#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Quizzes_Teacher extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Quizzes_Teacher.OnFragmentInteractionListener mListener;
    String quizName[]={"Quiz1","Quiz2","Quiz3","Quiz4"};
    String date[]={"25/11/2017","26/11/2017","27/11/2017","28/11/2017"};
    String time[]={"Time1","Time2","Time3","Time4"};

    public Quizzes_Teacher() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Quizzes_Teacher.
     */
    // TODO: Rename and change types and number of parameters
    public static Quizzes_Teacher newInstance(String param1, String param2) {
        Quizzes_Teacher fragment = new Quizzes_Teacher();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview=inflater.inflate(R.layout.fragment_quizzes_teacher, container, false);
        ListView listView=(ListView) rootview.findViewById(R.id.list_quiz_teacher);
        ArrayList<QuizListItem> quizzes_techer=new ArrayList<>();
        for(int i=0;i<4;i++)
        {
            QuizListItem quizView=new QuizListItem(quizName[i],date[i],time[i]);
            quizzes_techer.add(quizView);
        }
        final QuizAdapter quizAdapter=new QuizAdapter(getActivity(),quizzes_techer);
        listView.setAdapter(quizAdapter);
        Toast.makeText(getActivity(),"Student",Toast.LENGTH_LONG).show();
        return rootview;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
