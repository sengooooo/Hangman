import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Hangman extends JFrame implements ActionListener{

   String[] words = new String[201];

   int[] checked = new int [201]; // 나왔던 단어 체크하는 배열

   int word_length; // 단어의 길이

   int guessNum; // 맞추는 횟수 (10번만에 맞추는지 8번만에 맞추는지)

   int level; // 게임 난이도 

   char[] word1 = new char[12]; // 프로그램 안에서 돌아가는 char

   String[] slevel= {"Easy","Medium","Hard"}; // 난이도

   String[] word2 = new String[12]; // 화면에 출력할 String

   String check_word;

   double wins;

   double looses;

   double winningProsentige;

   String[] btn_Title={"A","B","C","D","E","F","G","H",

      "I","J","K","L","M","N","O","P","Q","R","S","T","U",

      "V","W","X","Y","Z"}; //26개

   JButton [] alpha= new JButton[26]; //버튼배열

   JButton begin = new JButton("BEGIN");

   JButton easy = new JButton("EASY");

   JButton medium = new JButton("MEDIUM");

   JButton hard = new JButton("HARD");

   JLabel text = new JLabel("Skill level: ", JLabel.LEFT); // 레벨 레이블로 띄우기

   

   JPanel displayTOP = new JPanel();

   JPanel display1 = new JPanel();     

   JPanel display2 = new JPanel();

   

   Font normalFont = new Font("Arial", Font.BOLD, 16);

   Font warningFont = new Font("Arial", Font.BOLD, 20);

   

   public Hangman() {

      setTitle("행맨 게임");

      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   

      setSize(850,700);

      setVisible(true);

   }

   

   public void init() {

   

      for(int i=0;i<btn_Title.length;i++) {

         alpha[i]=new JButton(btn_Title[i]);

      }



      for(int i=0;i<alpha.length;i++) {

         alpha[i].addActionListener(this);

      }



      begin.addActionListener(this);

      easy.addActionListener(this);

      medium.addActionListener(this);

      hard.addActionListener(this);

      

      GridLayout aaa = new GridLayout(3,0); // 전체 panel에 대한 layout 설정

      FlowLayout bbb = new FlowLayout();  // displayTOP에 대한 layout // begin버튼

                           FlowLayout ccc = new FlowLayout(); // display2에 대한 layout

                           GridLayout ddd = new GridLayout(3,10,5,5); // display1에 대한 layout // 알파벳

    

       Container root = getContentPane(); // 컨테이너 타입의 객체 root

      root.setLayout(aaa); //grid

      root.setBackground(Color.white);

      

      displayTOP.add(begin);

      displayTOP.setLayout(bbb);

      displayTOP.setBackground(Color.white);

               

      root.add(displayTOP); // TOP을 컨테이너에 등록

      display1.setLayout(ddd); 

      display1.setBackground(Color.white);

      

      for(int i=0;i<alpha.length;i++) {

         alpha[i].setBackground(Color.orange);

      } //버튼색깔

      

      for(int i=0;i<alpha.length;i++) {

         display1.add(alpha[i]);

      }//버튼넣기

      

      root.add(display1);

      

      display2.setLayout(ccc);

      display2.setBackground(Color.white);

      display2.add(text);

      display2.add(easy);

      display2.add(medium);

      display2.add(hard);

      root.add(display2);

      setContentPane(root);

      

      for(int i=0;i<alpha.length;i++) {

         alpha[i].setEnabled(false);

      }



      // begin이 눌리면 그때부터 활성화 (true)

      easy.setEnabled(false);

      medium.setEnabled(true);

      hard.setEnabled(true);

      

      words[0] = "abdicate"; words[1] = "allay"; words[2] = "accelerate"; words[3] = "affluent";

         words[4] = "annex"; words[5] = "approbation"; words[6] = "arrogant"; words[7] = "attribute";

         words[8] = "bereave"; words[9] = "benediction"; words[10] = "bounteous"; words[11] = "benefit";

         words[12] = "circuit"; words[13] = "cognition"; words[14] = "commonplace"; words[15] = "condense";

         words[16] = "conscript"; words[17] = "converse"; words[18] = "copious"; words[19] = "contraband";

         words[20] = "counter"; words[21] = "debase"; words[22] = "decipher"; words[23] = "decrease";

         words[24] = "degenerate"; words[25] = "depict"; words[26] = "derive"; words[27] = "dilatory";

         words[28] = "discard"; words[29] = "disillusion"; words[30] = "discrete"; words[31] = "distort";

         words[32] = "embargo"; words[33] = "embody"; words[34] = "encounter"; words[35] = "encroach";

         words[36] = "enroll"; words[37] = "entreat"; words[38] = "epoch"; words[39] = "elate";

         words[40] = "excerpt"; words[41] = "exempt"; words[42] = "ignoble"; words[43] = "illegitimate";

         words[44] = "illiterate"; words[45] = "impartial"; words[46] = "improvise"; words[47] = "impunity";

         words[48] = "incessant"; words[49] = "incomparable"; words[50] = "indolent"; words[51] = "infirm";

         words[52] = "impact"; words[53] = "implore"; words[54] = "impute"; words[55] = "entertain";

         words[56] = "maladroit"; words[57] = "malady"; words[58] = "malediction"; words[59] = "miscarry";

         words[60] = "mishap"; words[61] = "hostage"; words[62] = "obdurate"; words[63] = "obese";

         words[64] = "occult"; words[65] = "overall"; words[66] = "overlap"; words[67] = "outlaw";

         words[68] = "outright"; words[69] = "parable"; words[70] = "paradigm"; words[71] = "paralysis";

         words[72] = "peremptory"; words[73] = "perfidy"; words[74] = "pertain"; words[75] = "peruse";

         words[76] = "pervert"; words[77] = "predilection"; words[78] = "predominate"; words[79] = "prelude";

         words[80] = "premise"; words[81] = "profile"; words[82] = "progeny"; words[83] = "prolong";

         words[84] = "protract"; words[85] = "prudent"; words[86] = "ransom"; words[87] = "react";

         words[88] = "rebate"; words[89] = "rebuke"; words[90] = "recipe"; words[91] = "reclaim";

         words[92] = "recline"; words[93] = "recompense"; words[94] = "recondite"; words[95] = "recruit";

         words[96] = "recur"; words[97] = "remit"; words[98] = "retort"; words[99] = "subscribe";

         words[100] = "sojourn"; words[101] = "surge"; words[102] = "traduce"; words[103] = "transaction";

         words[104] = "transcribe"; words[105] = "transplant"; words[106] = "unarmed"; words[107] = "unfold";

         words[108] = "undue"; words[109] = "unload"; words[110] = "upgrade"; words[111] = "uplift";

         words[112] = "antique"; words[113] = "apostle"; words[114] = "archaeology"; words[115] = "autopsy";

         words[116] = "biennial"; words[117] = "binary"; words[118] = "bygone"; words[119] = "catastrophe";

         words[120] = "cathlic"; words[121] = "dilemma"; words[122] = "duel"; words[123] = "duplicate";

         words[124] = "diagnosis"; words[125] = "dialect"; words[126] = "eulogy"; words[127] = "euphria";

         words[128] = "euthanasia"; words[129] = "extraneous"; words[130] = "forfeit"; words[131] = "forgo";

         words[132] = "forlorn"; words[133] = "forsake"; words[134] = "forerunner"; words[135] = "foresight";

         words[136] = "foretell"; words[137] = "heterodox"; words[138] = "holocaust"; words[139] = "hyperbole";

         words[140] = "intrinsic"; words[141] = "microcosm"; words[142] = "microscopic"; words[143] = "metaphor";

         words[144] = "metaphysics"; words[145] = "monarchy"; words[146] = "monocracy"; words[147] = "monologue";

         words[148] = "monotony"; words[149] = "neologism"; words[150] = "neophyte"; words[151] = "nonchalant";

         words[152] = "omnibus"; words[153] = "omnipotent"; words[154] = "omnivorous"; words[155] = "panacea";

         words[156] = "panegyric"; words[157] = "pantomime"; words[158] = "periphery"; words[159] = "polygamy";

         words[160] = "postmortem"; words[161] = "mostscript"; words[162] = "symmetric"; words[163] = "symptom";

         words[164] = "synthesis"; words[165] = "telegraph"; words[166] = "telepathy"; words[167] = "unanimous";

         words[168] = "unification"; words[169] = "unison"; words[170] = "president"; words[171] = "withdraw";

         words[172] = "withhold"; words[173] = "withstand"; words[174] = "accord"; words[175] = "avail";

         words[176] = "belong"; words[177] = "benefit"; words[178] = "collect"; words[179] = "commit";

         words[180] = "complain"; words[181] = "decline"; words[182] = "depart"; words[183] = "effect";

         words[184] = "essay"; words[185] = "expose"; words[186] = "issue"; words[187] = "extreme";

         words[188] = "invent"; words[189] = "inferior"; words[190] = "parallel"; words[191] = "profit";

         words[192] = "proper"; words[193] = "reflect"; words[194] = "remove"; words[195] = "substitute";

         words[196] = "surrender"; words[197] = "transfer"; words[198] = "unconscious"; words[199] = "zealot";

         words[200] = "zenith"; 

      

      for(int i=0;i<checked.length;i++) {

         checked[i]=0; // 아직 선택되지 않은 단어 (0)으로 초기화

      }

      

      for(int i=0;i<12;i++) {

         word1[i]=' '; // character // 프로그램 안에서 맞는지 틀린지

         word2[i]=" "; // string // 화면에 내보낼 때 

      }

            

      /* 필요한 변수들의 초기치 설정 */

      wins = 0;

      looses =0 ;

      winningProsentige = 0.0;

   }



   public void paint(Graphics screen) {

      super.paint(screen);

      Graphics2D screen2D = (Graphics2D) screen;

      screen2D.setFont(warningFont);

   

      screen2D.drawLine(70,60,130,60);

      screen2D.drawLine(70,60,70,80);

      screen2D.drawLine(130,60,130,170);

      screen2D.drawLine(60,170,160,170);

      

      if(level == 0) {

         switch(guessNum) {

         case 1:

            screen2D.drawOval(60,80,20,20); // 얼굴

            break;

         case 2:

            screen2D.drawOval(60,80,20,20); // 얼굴

            break;

         case 3:

            screen2D.drawOval(60,80,20,20); // 얼굴

            screen2D.drawLine(70,100,70,110); // 몸통

            break;

         case 4:

            screen2D.drawOval(60,80,20,20); // 얼굴

            screen2D.drawLine(70,100,70,130); // 몸통

            break;

         case 5:

            screen2D.drawOval(60,80,20,20); // 얼굴

            screen2D.drawLine(70,100,70,130); // 몸통

            screen2D.drawLine(70,110,90,110); // 팔

            break;

         case 6:

            screen2D.drawOval(60,80,20,20); // 얼굴

            screen2D.drawLine(70,100,70,130); // 몸통

            screen2D.drawLine(50,110,90,110); // 팔

            break;

         case 7:

            screen2D.drawOval(60,80,20,20); // 얼굴

            screen2D.drawLine(70,100,70,130); // 몸통

            screen2D.drawLine(50,110,90,110); // 팔

            screen2D.drawLine(70,130,85,140); // 오른 다리

            break;

         case 8:

            screen2D.drawOval(60,80,20,20); // 얼굴

            screen2D.drawLine(70,100,70,130); // 몸통

            screen2D.drawLine(50,110,90,110); // 팔

            screen2D.drawLine(70,130,85,150); // 오른 다리

            break;

         case 9:

            screen2D.drawOval(60,80,20,20); // 얼굴

            screen2D.drawLine(70,100,70,130); // 몸통

            screen2D.drawLine(50,110,90,110); // 팔

            screen2D.drawLine(70,130,55,140); // 왼 다리

            screen2D.drawLine(70,130,85,150); // 오른 다리

            break;

         case 10:

            screen2D.drawOval(60,80,20,20); // 얼굴

            screen2D.drawLine(70,100,70,130); // 몸통

            screen2D.drawLine(50,110,90,110); // 팔

            screen2D.drawLine(70,130,55,150); // 왼 다리

            screen2D.drawLine(70,130,85,150); // 오른 다리

            break;

         }

      }

      

      

      if(level == 1) {

         switch(guessNum) {

         case 1:

            screen2D.drawOval(60,80,20,20); // 얼굴

            break;

         case 2:

            screen2D.drawOval(60,80,20,20); // 얼굴

            break;

         case 3:

            screen2D.drawOval(60,80,20,20); // 얼굴

            screen2D.drawLine(70,100,70,110); // 몸통

            break;

         case 4:

            screen2D.drawOval(60,80,20,20); // 얼굴

            screen2D.drawLine(70,100,70,130); // 몸통

            break;

         case 5:

            screen2D.drawOval(60,80,20,20); // 얼굴

            screen2D.drawLine(70,100,70,130); // 몸통

            screen2D.drawLine(70,110,90,110); // 팔

            break;

         case 6:

            screen2D.drawOval(60,80,20,20); // 얼굴

            screen2D.drawLine(70,100,70,130); // 몸통

            screen2D.drawLine(50,110,90,110); // 팔

            break;

         case 7:

            screen2D.drawOval(60,80,20,20); // 얼굴

            screen2D.drawLine(70,100,70,130); // 몸통

            screen2D.drawLine(50,110,90,110); // 팔

            screen2D.drawLine(70,130,85,150); // 오른 다리

            break;

         case 8:

            screen2D.drawOval(60,80,20,20); // 얼굴

            screen2D.drawLine(70,100,70,130); // 몸통

            screen2D.drawLine(50,110,90,110); // 팔

            screen2D.drawLine(70,130,55,150); // 왼 다리

            screen2D.drawLine(70,130,85,150); // 오른 다리

            break;

         }

      }

      

      if(level == 2) {

         switch(guessNum) {

         case 1:

            screen2D.drawOval(60,80,20,20); // 얼굴

            break;

         case 2:

            screen2D.drawOval(60,80,20,20); // 얼굴

            screen2D.drawLine(70,100,70,110); // 몸통

            break;

         case 3:

            screen2D.drawOval(60,80,20,20); // 얼굴

            screen2D.drawLine(70,100,70,130); // 몸통

            break;

         case 4:

            screen2D.drawOval(60,80,20,20); // 얼굴

            screen2D.drawLine(70,100,70,130); // 몸통

            screen2D.drawLine(50,110,90,110); // 팔

            break;

         case 5:

            screen2D.drawOval(60,80,20,20); // 얼굴

            screen2D.drawLine(70,100,70,130); // 몸통

            screen2D.drawLine(50,110,90,110); // 팔

            screen2D.drawLine(70,130,55,150); // 왼 다리

            break;

         case 6:

            screen2D.drawOval(60,80,20,20); // 얼굴

            screen2D.drawLine(70,100,70,130); // 몸통

            screen2D.drawLine(50,110,90,110); // 팔

            screen2D.drawLine(70,130,55,150); // 왼 다리

            screen2D.drawLine(70,130,85,150); // 오른 다리

            break;

         }

      }



      screen2D.setColor(Color.RED);

      screen2D.drawString(Integer.toString(guessNum)+" guesses left", 340, 240 );

      screen2D.setFont(normalFont);

      screen2D.setColor(Color.BLACK);

      screen2D.drawString("Current skill level: "+slevel[level], 300, 220 );

      screen2D.drawString("Wins ", 220, 200 );

      screen2D.drawString(Integer.toString((int)wins), 265, 200 );

      screen2D.drawString("Looses", 300, 200 );

      screen2D.drawString(Integer.toString((int)looses), 365, 200 );

      screen2D.drawString("WinningProsentige", 400, 200 );

      screen2D.drawString(Double.toString(winningProsentige)+"%", 555, 200 );

      

      for(int i=0;i<word_length;i++) {

    	  screen2D.drawString(word2[i], 300+i*20, 150);

      }

      //답이 맞은 단어에 대한 화면 표시

      //답이 틀린 단어에 대한 화면 표시   

      //시도한 횟수에 대하여 맞은 단어와 틀린 단어의 수 등을 표시 

   }

   public void wordSelect() {

      double sel_num = Math.random() * 201;// 0~200.xx

      int selection = (int) Math.floor(sel_num); // 0~200

      while(true) {  /* 이미 선택된 단어가 다시 선택되는 경우는 배제해야 함 */ // 나왔던 단어가 또 나오면 안됨

         if(checked[selection] == 0) { // 아직 뽑힌 단어가 아니라면 0

        	 checked[selection]=1; //index를 1로 체크

            break; //단어선정

         }

         else {

            sel_num = Math.random() * 201;// 0~200.xx

            selection = (int) Math.floor(sel_num); //selection 초기화 재실행

         }

      }

      

        String sel_Word;

      if(words[selection] != null) { // 고른 단어가 null이 아닐때까지

         sel_Word = words[selection].toLowerCase();

         word_length = sel_Word.length();



         char[] temp = sel_Word.toCharArray();   // character 배열로 변환

         for(int index1 = 0; index1 < word_length; index1++) {

             word1[index1] = temp[index1];

         }

          for(int index2 = 0; index2 < word_length; index2++) {

             word2[index2] = "_"; // .또는 _로 유저에게 단어의 철자 수를 알려줌

         }

      }

   }

   public void word_reset() { 

      for(int i=0;i<word2.length;i++) {

         word2[i]="_";

      }

      wordSelect();

   }

   public void spell_check(char spell) {

      int check_key = 0;

      for(int i=0; i<word2.length; i++) {

         if(word1[i] != ' ') {

            if(word1[i] == spell) {

               word2[i] = "" + spell;

               check_key = 1;

               repaint();

            }

         }

      }

      

      if(check_key == 0) { // 끝까지 다 찾았는데 check_key가 0이면 특정 알파(a)가 없음

         guessNum--;

         repaint();

      }

      Adjust_display();

      repaint();

   }

   

   public void Adjust_display() {

	   int correct=0;

	   

	   for(int i=0;i<word_length;i++) { //단어 추정 성공 여부 확인

		   if(word2[i]!="_") {correct+=1;}

	   }

	   

	   if(correct==word_length) { //단어 모두 맞혔을 경우

		   for(int i=0;i<alpha.length;i++) {

               alpha[i].setEnabled(false);

            }

            begin.setEnabled(true);

            if(level == 0) {

               medium.setEnabled(true);

               hard.setEnabled(true);

            } else if(level == 1) {

               easy.setEnabled(true);

               hard.setEnabled(true);

            } else if(level == 2) {

               easy.setEnabled(true);

               medium.setEnabled(true);

            }

            wins++;

            winningProsentige = (wins/(wins+looses))*100.0;

            repaint();  

         }

	   correct=0; //변수초기화

	       



      if(guessNum <= 0) {  // 단어 추정 실패 

         

          //버튼 눌릴 수 없게 만듬

         for(int i=0;i<alpha.length;i++) {

            alpha[i].setEnabled(false);

         }

         // 정답을 화면에 표시

         for(int i=0;i<12;i++){

            word2[i] = "" + word1[i];

         }

                        

         begin.setEnabled(true);   

         // level에 따른 버튼 활성화 작업 

         if(level == 0) {

            easy.setEnabled(false);

            medium.setEnabled(true);

            hard.setEnabled(true);

         } else if(level == 1) {

            easy.setEnabled(true); 

            medium.setEnabled(false);

            hard.setEnabled(true);

         } else if(level == 2) {

            easy.setEnabled(true);

            medium.setEnabled(true);

            hard.setEnabled(false);

         }

         looses++;

         winningProsentige = (wins/(wins+looses))*100.0;

         repaint();

         

         

      }



   }

   

   public void actionPerformed(ActionEvent event) {

      String typed = event.getActionCommand(); // 어떤 버튼을 눌렀는지 알려줌

      if(typed.equals("BEGIN")) {

         for(int i=0; i<12; i++){

            word1[i] = ' ';

            word2[i] = "_";

         }

            

         easy.setEnabled(false);

         medium.setEnabled(false);

         hard.setEnabled(false);



         if(level == 0) {

            guessNum = 10;

         } 

         else if(level == 1) {

            guessNum = 8;

         } 

         else if(level == 2) {

            guessNum = 6;

         }



         repaint();

         

         for(int i=0;i<alpha.length;i++) {

            alpha[i].setEnabled(true);

         }

         

         begin.setEnabled(false);

         word_reset();



         

      }

      

      

      

      for(int i=0;i<alpha.length;i++) {

    	  if(typed.equals(btn_Title[i])) {

    		  alpha[i].setEnabled(false);

    		  spell_check((char)('a'+i));

    	  }

      }

      

      if(typed.equals("EASY")) {

         easy.setEnabled(false);

         medium.setEnabled(true);

         hard.setEnabled(true);

         level = 0;

         repaint();

      }

      if(typed.equals("MEDIUM")) {

         easy.setEnabled(true);

         medium.setEnabled(false);

         hard.setEnabled(true);

         level = 1;

         repaint();

      }

      if(typed.equals("HARD")) {

         easy.setEnabled(true);

         medium.setEnabled(true);

         hard.setEnabled(false);

         level = 2;

         repaint();

      }

   }

   public static void main(String [] args) {

      Hangman h=new Hangman();

      h.init();

   }

}