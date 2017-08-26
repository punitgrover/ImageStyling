package ds.imagestyling.custom;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ds.imagestyling.R;
import ds.imagestyling.utils.Constants;
import ds.imagestyling.utils.Preferences;

public class Image_Text_Editor extends Activity implements OnClickListener {

	ImageView close;
	Button save,reset;
	TextView img_txt_editor_font,img_txt_editor_font_size,img_txt_editor_color;

	Editor editor;
	public EditText img_txt_editor_txt;
	ArrayList<String> font_size=new ArrayList<String>();

	private Display display;
	private int height1;
	private int width;
	HashMap<String, String> fontmap=new HashMap<String, String>();
	ArrayList<String> font_list=new ArrayList<String>();
	ArrayList<HashMap<String, String>> font_map1=new ArrayList<HashMap<String,String>>();

	private String path,fontsize;
	GridView gridViewColors;
    int[] colors = new int[]{ R.color.grid1,R.color.grid2, R.color.grid3,R.color.grid4, R.color.grid5,R.color.grid6,R.color.grid7,R.color.grid8, R.color.grid9,R.color.grid10, R.color.grid11,R.color.grid12};

    List<Integer> colorList = new ArrayList<Integer>();
	 ColorPickerAdapter1 adapter;
	 int selectedpos=-1;
	 TextView DefaultTXt;
	 ArrayList<String> DefaultTXT=new ArrayList<String>();
		String MY_FLURRY_APIKEY="HYFSD83DBWRCYH9YCPHJ";
    private int fontPos,fontSizePos;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.image__text__editor);

		display = getWindowManager().getDefaultDisplay();
		height1 = display.getHeight();
		width = display.getWidth();
		init();
		FontManager fontManager=new FontManager();
		fontmap = fontManager.enumerateFonts();
        if(Constants.color==-1||Constants.color==0)
            img_txt_editor_txt.setTextColor(getResources().getColor(R.color.black));
        else
            img_txt_editor_txt.setTextColor(getResources().getColor(Constants.color));
		//Constants.color=-5920337;
     //   font_list.get(Preferences.)
		/*for(int j=0;j<font_map1.size();j++){
			Log.e("System out", font_map1.get(j).get("path"));
			Log.e("System out", font_map1.get(j).get("filename"));

		}*/

		for (int i = 10; i <= 50; i++) {
			font_size.add(i+"");
		}
if(Preferences.getInt(Preferences.FONTSIZEPOS)>=0)
        img_txt_editor_font_size.setText(font_size.get(Preferences.getInt(Preferences.FONTSIZEPOS)));
        if(Preferences.getInt(Preferences.FONTSTYLEPOS)>=0) {
            img_txt_editor_font.setText(font_list.get(Preferences.getInt(Preferences.FONTSTYLEPOS)));
            Typeface tf1=Typeface.createFromFile(font_map1.get(Preferences.getInt(Preferences.FONTSTYLEPOS)).get("path"));
            img_txt_editor_txt.setTypeface(tf1);
        }DefaultTXT.add("Cheers");
		DefaultTXT.add("Best of Luck");
		DefaultTXT.add("Best Wishes");
		DefaultTXT.add("Congratulation");



	/*	for (int i = 0; i < colors.length; i++) {
			for (int j = 0; j < colors[i].length; j++) {
				colorList.add(Color.parseColor("#" + colors[i][j]));
			}
		}*/
		gridViewColors.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Constants.color=colorList.get(position);
				//img_txt_editor_txt.setTextColor(colorList.get(position));


			}
		});
     //   Constants.color=colorList.get(0);

		img_txt_editor_txt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
                if(Constants.color==-1||Constants.color==0)
                    img_txt_editor_txt.setTextColor(getResources().getColor(R.color.black));
                else
				img_txt_editor_txt.setTextColor(getResources().getColor(Constants.color));
				img_txt_editor_txt.setFocusableInTouchMode(true);
				img_txt_editor_txt.setFocusable(true);
				img_txt_editor_txt.setEnabled(true);

			}
		});


		img_txt_editor_txt.addTextChangedListener(new TextWatcher() {

			   @Override
			   public void onTextChanged(CharSequence s, int start, int before,
			     int count) {
			    // TODO Auto-generated method stub

			   }

			   @Override
			   public void beforeTextChanged(CharSequence s, int start, int count,
			     int after) {
			    // TODO Auto-generated method stub

			   }

			   @Override
			   public void afterTextChanged(Editable s) {
			    // TODO Auto-generated method stub
			    char x;
			    int[] t = new int[img_txt_editor_txt.getText().toString().length()];

			    for (int i = 0; i < img_txt_editor_txt.getText().toString()
			      .length(); i++) {
			     x = img_txt_editor_txt.getText().toString().charAt(i);
			     int z = (int) x;
			     t[i] = z;

			     if ((z > 47 && z < 58) || (z > 64 && z < 91)
			       || (z > 96 && z < 123) ||  z==32) {

			     } else {
			    	 Toast.makeText(Image_Text_Editor.this,"Special characters are not allowed to type", 3000) .show();
			    	 img_txt_editor_txt.setText(img_txt_editor_txt
			        .getText()
			        .toString()
			        .substring(
			          0,
			          (img_txt_editor_txt.getText().toString()
			            .length()) - 1));
			    	 img_txt_editor_txt.setSelection(img_txt_editor_txt.getText()
			        .length());
			     }

			    }
			   }
			  });

	}



	private void init() {
		save=(Button)findViewById(R.id.img_txt_editor_save);
		reset=(Button)findViewById(R.id.img_txt_editor_reset);
		close=(ImageView)findViewById(R.id.img_txt_editor_close);
		img_txt_editor_font=(TextView)findViewById(R.id.img_txt_editor_font);
		img_txt_editor_font_size=(TextView)findViewById(R.id.img_txt_editor_font_size);
		img_txt_editor_txt=(EditText)findViewById(R.id.img_txt_editor_txt);
		img_txt_editor_color=(TextView)findViewById(R.id.img_txt_editor_color);
		gridViewColors = (GridView) findViewById(R.id.gridViewColors2);
        selectedpos=Constants.pos;
		adapter=new ColorPickerAdapter1(Image_Text_Editor.this,colors);
		gridViewColors.setAdapter(adapter);
		DefaultTXt=(TextView)findViewById(R.id.DefaultTXt);
		DefaultTXt.setOnClickListener(this);

		img_txt_editor_txt.setText("");


		save.setOnClickListener(this);
		reset.setOnClickListener(this);
		close.setOnClickListener(this);
		img_txt_editor_font_size.setOnClickListener(this);
		img_txt_editor_font.setOnClickListener(this);
		img_txt_editor_color.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v==save) {
			if (fontsize==null) {
				fontsize="25";
			}if (path==null) {
				path="/system/fonts/AndroidClock.ttf";
			} if (Constants.color==0) {
				Constants.color=-1;
			}
			Preferences.saveValue(Preferences.FONTSIZE,fontsize);
			Preferences.saveValue(Preferences.FONTTYPE,path);
			Preferences.saveValue(Preferences.FONT,img_txt_editor_txt.getText().toString());
			Preferences.saveValue(Preferences.SAVE,"yes");
			Preferences.saveValue(Preferences.SAVE,"yes");
			Preferences.saveValue(Preferences.FONTSIZEPOS,fontSizePos);
			Preferences.saveValue(Preferences.FONTSTYLEPOS,fontPos);
			Constants.pos=selectedpos;

			Image_Text_Editor.this.finish();
		}else if (v==reset) {
            Preferences.saveValue(Preferences.FONTSIZEPOS,-1);
            Preferences.saveValue(Preferences.FONTSTYLEPOS,-1);
            Typeface tf1=Typeface.createFromFile("/system/fonts/AndroidClock.ttf");
            img_txt_editor_txt.setTypeface(tf1);
			InputMethodManager imm = (InputMethodManager)getSystemService(
				      Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(reset.getWindowToken(), 0);
				img_txt_editor_txt.setText("");
				img_txt_editor_font_size.setText("Font Size");
            img_txt_editor_font.setText("Font");
				fontsize="25";
				selectedpos=-1;
            Constants.pos=selectedpos;
				img_txt_editor_txt.setTextColor(-1);
				adapter=new ColorPickerAdapter1(Image_Text_Editor.this,colors);
				gridViewColors.setAdapter(adapter);
				gridViewColors.setSelection(0);
			Preferences.saveValue(Preferences.SAVE,"no");
				Constants.color=-1;

		}else if (v==close) {
			InputMethodManager imm = (InputMethodManager)getSystemService(
				      Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(img_txt_editor_txt.getWindowToken(), 0);

				/*new AlertDialog.Builder(Image_Text_Editor.this).setTitle("").setMessage("Are you sure you want to close?")
			    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) {
			        	dialog.dismiss();*/
						Preferences.saveValue(Preferences.FONT,"");
						Image_Text_Editor.this.finish();

			     /*   }
			     })
			    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) {
			        	dialog.dismiss();

			        }

				} ).setIcon(android.R.drawable.ic_dialog_alert)
			     .show();
			*/

		}else if (v==img_txt_editor_font_size) {
			InputMethodManager imm = (InputMethodManager)getSystemService(
				      Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(img_txt_editor_font_size.getWindowToken(), 0);
			fontSizeDropdowndialog(font_size);
		}else if (v==img_txt_editor_font) {
			InputMethodManager imm = (InputMethodManager)getSystemService(
				      Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(img_txt_editor_font.getWindowToken(), 0);
			fontstyleDropdowndialog(font_list);
		}else if (v==img_txt_editor_color) {
			//fontColorDropdowndialog();
		}else if (v==DefaultTXt) {
			InputMethodManager imm = (InputMethodManager)getSystemService(
				      Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(img_txt_editor_txt.getWindowToken(), 0);
			DefaultTXTDropdowndialog(DefaultTXT);

		}
	}




	public void DefaultTXTDropdowndialog(final ArrayList<String> DefaultTXT) {
		AlertDialog.Builder dialog1 = new AlertDialog.Builder(Image_Text_Editor.this);
		LayoutInflater inflater = (LayoutInflater) Image_Text_Editor.this
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.listviewfile, null, false);


		final ListView title = (ListView) layout.findViewById(R.id.dialoglist);

		if (DefaultTXT.size() <= 1) {
			title.getLayoutParams().height = (height1 * 10) / 100;
		} else if (DefaultTXT.size() == 2) {
			title.getLayoutParams().height = (height1 * 28) / 100;
		} else {
			title.getLayoutParams().height = (height1 * 38) / 100;
		}

		final AlertDialog Dial = dialog1.create();
		title.setAdapter(new ArrayAdapter<String>(Image_Text_Editor.this, R.layout.list_cell, DefaultTXT));

		Dial.setView(layout);
		Dial.getWindow().setGravity(Gravity.BOTTOM);
		title.setOnItemClickListener(new OnItemClickListener() {



			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {

				Dial.dismiss();

				img_txt_editor_txt.setText(DefaultTXT.get(position));
				img_txt_editor_txt.setFocusable(false);
				img_txt_editor_txt.setEnabled(true);
				int pos = position + 1;

				}


		});

		Dial.show();
	}

	public void fontstyleDropdowndialog(final ArrayList<String> droplist1) {
		AlertDialog.Builder dialog1 = new AlertDialog.Builder(Image_Text_Editor.this);
		LayoutInflater inflater = (LayoutInflater) Image_Text_Editor.this
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.listviewfile, null, false);


		final ListView title = (ListView) layout.findViewById(R.id.dialoglist);

		if (font_list.size() <= 1) {
			title.getLayoutParams().height = (height1 * 10) / 100;
		} else if (font_list.size() == 2) {
			title.getLayoutParams().height = (height1 * 28) / 100;
		} else {
			title.getLayoutParams().height = (height1 * 38) / 100;
		}

		final AlertDialog Dial = dialog1.create();
		title.setAdapter(new ArrayAdapter<String>(Image_Text_Editor.this, R.layout.list_cell, font_list));

		Dial.setView(layout);
		Dial.getWindow().setGravity(Gravity.BOTTOM);
		title.setOnItemClickListener(new OnItemClickListener() {



			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {

				Dial.dismiss();
				if (font_list.get(position).equalsIgnoreCase("null")) {

				}else{
				img_txt_editor_font.setText(font_list.get(position));

				int pos = position + 1;


				String font="fonts/"+font_list.get(position);
				Typeface tf1=Typeface.createFromFile(font_map1.get(position).get("path"));
				img_txt_editor_txt.setTypeface(tf1);
				path=font_map1.get(position).get("path");
                    fontPos=position;
				}
			}

		});

		Dial.show();
	}

	public void fontSizeDropdowndialog(final ArrayList<String> droplist1) {
		AlertDialog.Builder dialog1 = new AlertDialog.Builder(Image_Text_Editor.this);
		LayoutInflater inflater = (LayoutInflater) Image_Text_Editor.this
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		View layout = inflater.inflate(R.layout.listviewfile, null, false);


		final ListView title = (ListView) layout.findViewById(R.id.dialoglist);
	/*	LinearLayout.LayoutParams buttonLayoutParams = new  LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		buttonLayoutParams.setMargins(0, 20, 0, 0);
		title.setLayoutParams(buttonLayoutParams);*/
		if (font_size.size() <= 1) {
			title.getLayoutParams().height = (height1 * 10) / 100;
		} else if (font_size.size() == 2) {
			title.getLayoutParams().height = (height1 * 28) / 100;
		} else {
			title.getLayoutParams().height = (height1 * 38) / 100;
		}

		final AlertDialog Dial = dialog1.create();
		title.setAdapter(new ArrayAdapter<String>(Image_Text_Editor.this,R.layout.list_cell, font_size));

		Dial.setView(layout);
		Dial.getWindow().setGravity(Gravity.BOTTOM);

		title.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {

				Dial.dismiss();
				img_txt_editor_font_size.setText(font_size.get(position));
				int pos = position + 1;


				fontsize=font_size.get(position);
                fontSizePos=position;
			}

		});

		Dial.show();
	}


	public class FontManager {
		public HashMap<String, String> enumerateFonts() {
			String[] fontdirs = { "/system/fonts", "/system/font",
					"/data/fonts" };
			HashMap<String, String> fonts = new HashMap<String, String>();
			 TTFAnalyzer analyzer = new TTFAnalyzer();

			for (String fontdir : fontdirs) {
				File dir = new File(fontdir);

				if (!dir.exists())
					continue;

				File[] files = dir.listFiles();

				if (files == null)
					continue;

				for (File file : files) {
					String fontname = analyzer.getTtfFontName(file.getAbsolutePath());

					if (fontname != null){
						font_list.add(fontname);
						HashMap< String, String> map=new HashMap<String, String>();
						map.put("filename", fontname);
						map.put("path", file.getAbsolutePath());
						font_map1.add(map);
					}
				}
			}

			return fonts.isEmpty() ? null : fonts;
		}
	}
	public class ColorPickerAdapter1 extends BaseAdapter {

		private Context context;

		int colorGridColumnWidth;
		int colorGridColumnHeight;
		String fromactivity="";
        int[] colorList1;

		public ColorPickerAdapter1(Context context,int[] colorList) {
			this.context = context;
			this.colorList1=colorList;
			colorGridColumnWidth = 65;
			colorGridColumnHeight=65;

		}

		public View getView(final int position,  View convertView, ViewGroup parent) {
			ImageView imageView;
			if (convertView == null) {
				imageView = new ImageView(context);
				imageView.setLayoutParams(new GridView.LayoutParams(colorGridColumnWidth, colorGridColumnWidth));
                float scale = getResources().getDisplayMetrics().density;
                int dpAsPixels = (int) (2*scale + 0.5f);
                imageView.setPadding(dpAsPixels,dpAsPixels,dpAsPixels,dpAsPixels);
			} else {
				imageView = (ImageView) convertView;
			}

            imageView.setImageResource(R.drawable.tround_bg);
            imageView.setColorFilter(ContextCompat.getColor(context, colorList1[position]));
			imageView.setId(position);
			if (selectedpos==position) {
				imageView.setBackground(getResources().getDrawable(R.drawable.rd_circle));
				gridViewColors.setSelection(selectedpos);
			}


			imageView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					selectedpos=position;

					Constants.color=colors[position];
					img_txt_editor_txt.setTextColor(getResources().getColor(Constants.color));
					adapter=new ColorPickerAdapter1(Image_Text_Editor.this,colors);
					gridViewColors.setAdapter(adapter);


					Toast.makeText(context, "Your color has been selected", 1000).show();



				}
			});
			return imageView;
		}

		public int getCount() {
			return colorList1.length;
		}

		public Object getItem(int position) {
			return null;
		}

		public long getItemId(int position) {
			return 0;
		}
	}
}


class TTFAnalyzer {
	public String getTtfFontName(String fontFilename) {
		try {
			// Parses the TTF file format.
			// See http://developer.apple.com/fonts/ttrefman/rm06/Chap6.html
			m_file = new RandomAccessFile(fontFilename, "r");

			// Read the version first
			int version = readDword();

			// The version must be either 'true' (0x74727565) or 0x00010000
			if (version != 0x74727565 && version != 0x00010000)
				return null;

			// The TTF file consist of several sections called "tables", and we
			// need to know how many of them are there.
			int numTables = readWord();

			// Skip the rest in the header
			readWord(); // skip searchRange
			readWord(); // skip entrySelector
			readWord(); // skip rangeShift

			// Now we can read the tables
			for (int i = 0; i < numTables; i++) {
				// Read the table entry
				int tag = readDword();
				readDword(); // skip checksum
				int offset = readDword();
				int length = readDword();

				// Now here' the trick. 'name' field actually contains the
				// textual string name.
				// So the 'name' string in characters equals to 0x6E616D65
				if (tag == 0x6E616D65) {
					// Here's the name section. Read it completely into the
					// allocated buffer
					byte[] table = new byte[length];

					m_file.seek(offset);
					read(table);

					// This is also a table. See
					// http://developer.apple.com/fonts/ttrefman/rm06/Chap6name.html
					// According to Table 36, the total number of table records
					// is stored in the second word, at the offset 2.
					// Getting the count and string offset - remembering it's
					// big endian.
					int count = getWord(table, 2);
					int string_offset = getWord(table, 4);

					// Record starts from offset 6
					for (int record = 0; record < count; record++) {
						// Table 37 tells us that each record is 6 words -> 12
						// bytes, and that the nameID is 4th word so its offset
						// is 6.
						// We also need to account for the first 6 bytes of the
						// header above (Table 36), so...
						int nameid_offset = record * 12 + 6;
						int platformID = getWord(table, nameid_offset);
						int nameid_value = getWord(table, nameid_offset + 6);

						// Table 42 lists the valid name Identifiers. We're
						// interested in 4 but not in Unicode encoding (for
						// simplicity).
						// The encoding is stored as PlatformID and we're
						// interested in Mac encoding
						if (nameid_value == 4 && platformID == 1) {
							// We need the string offset and length, which are
							// the word 6 and 5 respectively
							int name_length = getWord(table, nameid_offset + 8);
							int name_offset = getWord(table, nameid_offset + 10);

							// The real name string offset is calculated by
							// adding the string_offset
							name_offset = name_offset + string_offset;

							// Make sure it is inside the array
							if (name_offset >= 0
									&& name_offset + name_length < table.length)
								return new String(table, name_offset,
										name_length);
						}
					}
				}
			}

			return null;
		} catch (FileNotFoundException e) {
			// Permissions?
			return null;
		} catch (IOException e) {
			// Most likely a corrupted font file
			return null;
		}
	}

	// Font file; must be seekable
	private RandomAccessFile m_file = null;

	// Helper I/O functions
	private int readByte() throws IOException {
		return m_file.read() & 0xFF;
	}

	private int readWord() throws IOException {
		int b1 = readByte();
		int b2 = readByte();

		return b1 << 8 | b2;
	}

	private int readDword() throws IOException {
		int b1 = readByte();
		int b2 = readByte();
		int b3 = readByte();
		int b4 = readByte();

		return b1 << 24 | b2 << 16 | b3 << 8 | b4;
	}

	private void read(byte[] array) throws IOException {
		if (m_file.read(array) != array.length)
			throw new IOException();
	}

	// Helper
	private int getWord(byte[] array, int offset) {
		int b1 = array[offset] & 0xFF;
		int b2 = array[offset + 1] & 0xFF;

		return b1 << 8 | b2;
	}
	
	
	

}
