package hr.web.test;

import hr.web.test.R;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class WebAppInterface {
	WebActivity wa=null;
	String firstinput="0";
	String result;
	Context c;
    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        wa=(WebActivity)c;
    }
    public void showToast(String toast) {
    	Log.d("test","event fired");
    	wa.button.setText("new text");
        //Toast.makeText(wa, toast, Toast.LENGTH_LONG).show();
    }
    public void reload(){
    	wa.mHandler.post(new Runnable() {
			public void run() {
				WebAppInterface.this.wa.myWebView.reload();
				Log.d("try", "reload");
			}
		});
	}
    public void addNum(String num){
    	firstinput = firstinput+""+num;
    	Log.d("Web", firstinput);

    }
    
    public void addOperator(String operator){
    	firstinput =firstinput+""+operator;
    	Log.d("Web", firstinput);
    }
    
    public String getResult(){
    	result = calculate(firstinput)+"";
    	return result;
    }
    
    
    public void testMethod(){
    	Log.d("changed event","success");
    	//wa.button.setText("changed name");
    	/*wa.mHandler.post(new Runnable() {
			public void run() {
				Button btn= (Button)WebAppInterface.this.wa.findViewById(R.id.button1);
				btn.setText("new Changed");
			}
		});*/
    	wa.runOnUiThread(new Runnable() {
			public void run() {
				//Button btn= (Button)WebAppInterface.this.wa.findViewById(R.id.button1);
				//btn.setText("Changed");
			}
		});
	}

    public static double calculate(final String str) {
        class Parser {
            int pos = -1, c;

            void eatChar() {
            	try{
                c = (++pos < str.length()) ? str.charAt(pos) : -1;
            	}
            	catch (Exception e) {
					// TODO: handle exception
            		e.printStackTrace();
				}
            }

            void eatSpace() {
                while (Character.isWhitespace(c)) eatChar();
            }

            double parse() {
                eatChar();
                double v = parseExpression();
                if (c != -1) throw new RuntimeException("Problem: " + (char)c);
                return v;
            }


            double parseExpression() {
                double v = parseTerm();
                for (;;) {
                    eatSpace();
                 // addition
                    try{
                    if (c == '+') { 
                        eatChar();
                        v += parseTerm();
                    } 
                 // subtraction
                    else if (c == '-') { 
                        eatChar();
                        v -= parseTerm();
                    } 
                    else {
                        return v;
                    }
                    }catch (Exception e) {
						// TODO: handle exception
                    	e.printStackTrace();
					}
                }
            }

            double parseTerm() {
                double v = parseFactor();
                for (;;) {
                    eatSpace();
                 // division
                    try{
                    if (c == '/') { 
                        eatChar();
                        v /= parseFactor();
                    } 
                 // multiplication
                    else if (c == '*' || c == '(') { 
                        if (c == '*') eatChar();
                        v *= parseFactor();
                    }
                    else {
                        return v;
                    }
                    }
                    catch (Exception e) {
						// TODO: handle exception
                    	e.printStackTrace();
					}
                }
            }

            double parseFactor() {
                double v;
                boolean negate = false;
                eatSpace();
             // unary plus & minus
                if (c == '+' || c == '-') { 
                    negate = c == '-';
                    eatChar();
                    eatSpace();
                }
             // brackets
                if (c == '(') { 
                    eatChar();
                    v = parseExpression();
                    if (c == ')') eatChar();
                }
                
                else { 
                    int startIndex = this.pos;
                    while ((c >= '0' && c <= '9') || c == '.') eatChar();
                    try{
                    if (pos == startIndex) throw new RuntimeException("Problem: " + (char)c);
                    }

                    catch (Exception e) {
						// TODO: handle exception
                    	e.printStackTrace();
					}
                    v = Double.parseDouble(str.substring(startIndex, pos));
                    
                }

                eatSpace();
                if (negate) v = -v; 
                return v;
            }
        }
        return new Parser().parse();
    }
    
    public void clear_method()
    {
    	firstinput="0";
    }
    
}
