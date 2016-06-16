package cos.test;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class QuestionsAdapter extends ArrayAdapter<Question>{

    @Bind(R.id.tvUserName)
    TextView tvUserName;
    @Bind(R.id.tvDateCreateQuestion)
    TextView tvDateCreateQuestion;
    @Bind(R.id.tvCountAnswers)
    TextView tvCountAnswers;
    @Bind(R.id.tvQuestion)
    TextView tvQuestion;
    @Bind(R.id.imgAva)
    ImageView imgAva;

    List<Question> questions;

    public QuestionsAdapter(Context context, List<Question> questions) {
        super(context, R.layout.question, questions);
        this.questions = questions;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.question, parent, false);
        }
        ButterKnife.bind(this, view);
        final Question currentQuestion = questions.get(position);

        tvUserName.setText(currentQuestion.getUserName());
        final Date date = new Date(Long.valueOf(currentQuestion.getDateCreateQuestion()));
        tvDateCreateQuestion.setText(String.valueOf(date));
        tvCountAnswers.setText("Answers: " + currentQuestion.getCountAnswers());
        tvQuestion.setText(currentQuestion.getQuestion());

        if (currentQuestion.getImage() != "") {
            Picasso.with(getContext()).load(currentQuestion.getImage()).into(imgAva);
        }

        imgAva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), QuestionActivity.class);
                if (currentQuestion.getLinkProfile() != "") {
                    intent.putExtra("link", currentQuestion.getLinkProfile());
                    v.getContext().startActivity(intent);
                }

            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentQuestion.getLinkQuestion()));
                v.getContext().startActivity(intent);
            }
        });

        return view;
    }
}
