    package com.example.alonzo_lectea2_lotto;

    import android.os.Bundle;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Spinner;
    import android.widget.TextView;

    import androidx.activity.EdgeToEdge;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.graphics.Insets;
    import androidx.core.view.ViewCompat;
    import androidx.core.view.WindowInsetsCompat;

    import java.util.HashSet;
    import java.util.Set;

    public class MainActivity extends AppCompatActivity {

        TextView[] winnerNumbers;
        TextView prizeValue;
        EditText[] lottoNumbers;
        Button checkResults, tryAgain;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_main);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });

            lottoNumbers = new EditText[]{findViewById(R.id.editTextOne),
                    findViewById(R.id.editTextTwo), findViewById(R.id.editTextThree),
                    findViewById(R.id.editTextFour), findViewById(R.id.editTextFive),
                    findViewById(R.id.editTextSix)};

            winnerNumbers = new TextView[]{findViewById(R.id.winOne), findViewById(R.id.winTwo), findViewById(R.id.winThree),
            findViewById(R.id.winFour), findViewById(R.id.winFive), findViewById(R.id.winSix)};

            prizeValue = findViewById(R.id.prizeValue);

            checkResults = (Button) findViewById(R.id.buttonCheck);
            tryAgain = (Button) findViewById(R.id.buttonTryAgain);
        }

        public void onClickCheckResults(View view) {
            if(errorChecker(lottoNumbers)) return;

            LottoCompute computations = new LottoCompute();

            Integer[] lottoConvert = new Integer[6];
            for(int i = 0; i<6; i++){
                lottoConvert[i] = Integer.parseInt(lottoNumbers[i].getText().toString());
            }

            computations.setLottoNumbers(lottoConvert);
            computations.setWinnerNumbers();
            computations.setMatchedQuantity();

            displayWinnerNumbers(computations.getWinnerNumbers());
            prizeValue.setText(computations.getPrizeValue());
            prizeValue.setVisibility(View.VISIBLE);

            checkResults.setVisibility(View.GONE);
            tryAgain.setVisibility(View.VISIBLE);
        }

        public void displayWinnerNumbers(Integer[] winners){
            for(int i=0; i<winners.length; i++) {
                winnerNumbers[i].setText(String.format("%s", winners[i].toString()));
            }
        }

        public boolean errorChecker(EditText[] lottoNumbers) {
            boolean hasError = false;
            Set<Integer> checkedNumbers = new HashSet<>();

            for (EditText lottoNumber : lottoNumbers) {
                String input = lottoNumber.getText().toString().trim();

                if (input.isEmpty()) {
                    lottoNumber.setError("Required");
                    hasError = true;
                    continue;
                }

                int number = Integer.parseInt(input);

                if (number < 1 || number > 42) {
                    lottoNumber.setError("Must be between 1 and 42");
                    hasError = true;
                    continue;
                }

                if (!checkedNumbers.add(number)) {
                    lottoNumber.setError("Duplicate!");
                    hasError = true;
                }
            }

            return hasError;
        }

        public void onClickTryAgain(View view) {
            for (EditText lottoNumber : lottoNumbers) {
                lottoNumber.setText("");
            }

            for (TextView winnerNumber : winnerNumbers) {
                winnerNumber.setText("");
            }
            prizeValue.setVisibility(View.GONE);
            tryAgain.setVisibility(View.GONE);
            checkResults.setVisibility(View.VISIBLE);
        }
    }