package cos.test;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.txtQuestion)
    EditText txtQuestions;
    @Bind(R.id.lv)
    ListView lv;
    private ArrayList<Question> questions= new ArrayList<>();

    private QuestionsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Question currentQuestion = questions.get(position);
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(currentQuestion.getLinkProfile()));
                view.getContext().startActivity(intent);
            }
        });

        adapter = new QuestionsAdapter(this, questions);
        lv.setAdapter(adapter);
    }


    public void onClickGetQuestions(View view) {
        if (txtQuestions.getText().length() == 0) {
            questions.clear();
            adapter.notifyDataSetChanged();
            return;
        }
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://api.stackexchange.com/2.2/search/advanced?order=desc&sort=activity&site=stackoverflow&q=" +
                txtQuestions.getText();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        questions.clear();

                        try {
                            addDataInList(response);
                        } catch (JSONException e) {
                           e.printStackTrace();
                        }

                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
                queue.add(jsonObjectRequest);
    }

    private void addDataInList(JSONObject response) throws JSONException {
        JSONArray items = response.getJSONArray("items");

        for (int i = 0; i < items.length(); i++) {
            int id = items.getJSONObject(i).getInt("question_id");
            String title = items.getJSONObject(i).getString("title");
            String dateCreate = items.getJSONObject(i).getString("creation_date");
            int count = items.getJSONObject(i).getInt("answer_count");
            String link = items.getJSONObject(i).getString("link");

            JSONObject owner = items.getJSONObject(i).getJSONObject("owner");
            String image = "";
            String linkProfile = "";
            try {
                image = owner.getString("profile_image");
            }catch (JSONException e) {
                System.out.println(e);
            }
            try {
                linkProfile = owner.getString("link");
            }catch (JSONException e) {
                System.out.println(e);
            }
            String userName = owner.getString("display_name");
            Question question = new Question(id, title, image, userName, dateCreate, count, link, linkProfile);
            questions.add(question);
        }
    }
}
