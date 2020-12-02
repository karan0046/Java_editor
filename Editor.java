package GUI;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

public class Editor implements ActionListener{

	public static void main(String[] args) {
		   new Editor();
	}

	JFrame jf = new JFrame();	
	JMenuBar jmb = new JMenuBar();
	JTextArea textarea = new JTextArea();
	JFileChooser fc = new JFileChooser();	
	JScrollPane scroll = new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	Font f = new Font("Consolas",Font.PLAIN,16);//Dialog input
	
	
	
	// menu bar options
	JMenu create = new JMenu("Create");
	JMenu open = new JMenu("Open");
	JMenu save = new JMenu("Save");
	JMenu edit = new JMenu("Edit");
	JMenu compile = new JMenu("Compile");
	JMenu run = new JMenu("Run");
	
	
	// create options
	//JMenuItem cp = new JMenuItem("New Package");
	JMenuItem cc = new JMenuItem("Class");
	JMenuItem ci = new JMenuItem("Interface");
	JMenuItem cs = new JMenuItem("Save");
	JMenuItem ce = new JMenuItem("Exit");
	JMenu cp = new JMenu("New");
	//JMenuItem fprint = new JMenuItem("Print");
	
	
	// edit options
	JMenuItem efo = new JMenu("Font");
	JMenuItem fst = new JMenuItem("Font Style");
	JMenuItem fsi = new JMenuItem("Font Size");
	JMenuItem eu = new JMenuItem("Open");
	JMenuItem ecu = new JMenuItem("Cut             Ctrl+X");
	JMenuItem eco = new JMenuItem("Copy          Ctrl+C");
	JMenuItem ep = new JMenuItem("Paste         Ctrl+V");
	JMenuItem es = new JMenuItem("Save");
	JMenuItem esa = new JMenuItem("Select All     Ctrl+A");
	
	
	
	// open options
	JMenuItem oc = new JMenuItem("Open");
	//JMenuItem oo = new JMenuItem("open");
	
	// save options
	JMenuItem sc = new JMenuItem("Save");
	
	// compile options
	JMenuItem cosc = new JMenuItem("Save");
	JMenuItem coc = new JMenuItem("Package File");
	JMenuItem cor = new JMenuItem("Normal File");
	
	//run options
	JMenuItem rc = new JMenuItem("Package File");
	JMenuItem rr = new JMenuItem("Normal File");
		
	public Editor() {
		
		

		//UIManager.put(jmb.getBackground(), Color.orange);
		// create 
		create.setMnemonic(KeyEvent.VK_C);
		create.add(cp);
		cp.add(cc);		
		cp.add(ci);		
		create.add(cs);		
		create.addSeparator();
		create.add(ce);
		//file.add()
		//fprint.addActionListener(this);
		cp.addActionListener(this);
		cc.addActionListener(this);
		ci.addActionListener(this);
		cs.addActionListener(this);
		ce.addActionListener(this);
		
		
		// edit 
		edit.setMnemonic(KeyEvent.VK_E);
		edit.add(efo);
		edit.addSeparator();
		efo.add(fst);
		efo.add(fsi);
		edit.add(eu);
		edit.addSeparator();
		edit.add(ecu);
		edit.add(eco);
		edit.add(ep);
		edit.add(es);
		edit.addSeparator();
		edit.add(esa);
		efo.addActionListener(this);
		fst.addActionListener(this);
		fsi.addActionListener(this);
		eu.addActionListener(this);
		ecu.addActionListener(this);
		eco.addActionListener(this);
		ep.addActionListener(this);
		es.addActionListener(this);
		esa.addActionListener(this);
		
		
		// save 
		save.setMnemonic(KeyEvent.VK_S);
		save.add(sc);
		sc.addActionListener(this);
		//format.add(fof);
		
		// open  
		open.setMnemonic(KeyEvent.VK_O);
		open.add(oc);
		oc.addActionListener(this);
		
		//compile 
		compile.setMnemonic(KeyEvent.VK_C);
		compile.add(cosc);
		compile.addSeparator();
		compile.add(coc);
		compile.addSeparator();
		compile.add(cor);
		cosc.addActionListener(this);
		coc.addActionListener(this);
		cor.addActionListener(this);
		
		//run
		run.setMnemonic(KeyEvent.VK_R);
		run.add(rc);
		run.addSeparator();
		run.add(rr);
		rc.addActionListener(this);
		rr.addActionListener(this);
		
		textarea.setFont(f);
		//textarea.setLineWrap(true);
		//textarea.setWrapStyleWord(true);
		
		scroll.setSize(800,490);
		// menu bar options added
		jmb.add(create);
		jmb.add(open);
		jmb.add(edit);
		jmb.add(save);
		jmb.add(compile);
		jmb.add(run);
		
		
		jmb.setBackground(Color.LIGHT_GRAY);
		jf.add(scroll);
		jf.setJMenuBar(jmb);
		jf.setTitle("Java-Editor");
		jf.setSize(1200,800);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);

		jf.setVisible(true);
		
	     
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//if(e.getActionCommand().equals("Create")) {
			
		//}
		if(e.getActionCommand().equals("Save")) {
				if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					//File fi = new File(fc.getSelectedFile().getAbsolutePath()); 
					FileWriter fw = null;
					try {
						fw = new FileWriter(fc.getSelectedFile().getAbsolutePath(),false);//+".txt"
						BufferedWriter w = new BufferedWriter(fw); 
						w.write(textarea.getText());  
	                    w.flush(); 
	                    w.close();
						//textarea.write(fw);
						fw.close();
					}
					catch(IOException ev) {
						JOptionPane.showMessageDialog(null, ev.getMessage()); 
						ev.printStackTrace();
					}
				}
				//else 
					//JOptionPane.showMessageDialog(null, "the user cancelled the operation");
		}
		if(e.getActionCommand().equals("Open")) {
			if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				FileReader fr = null;
				try {
					fr = new FileReader(fc.getSelectedFile().getAbsolutePath());
					textarea.read(fr,null);
					fr.close();
					//setTitle("working");
				}
				catch(IOException ev) {
					JOptionPane.showMessageDialog(null, ev.getMessage()); 
					ev.printStackTrace();
					
				}
			}
		}
		if(e.getActionCommand().equals("Class")) {
			textarea.setText("");
		}
		if(e.getActionCommand().equals("Interface")) {
			textarea.setText("");
		}
		if(e.getSource().equals(fst)) {
			String fonttypes[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
			String s = (String)JOptionPane.showInputDialog(null, "Choose the Font Style","Font style", JOptionPane.PLAIN_MESSAGE,null, fonttypes,"ham");

			//If a string was returned, say so.
			if ((s != null) && (s.length() > 0)) {
			    textarea.setFont(new Font(s,Font.PLAIN,16));
			}

			//JOptionPane.showMessageDialog(null, "working"); 
		}
		if(e.getSource().equals(fsi)) {
			//Font font;
			//String array[] = {"10","12","14","16","18"};
			//String fonttypes[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
			String sz = (String)JOptionPane.showInputDialog(null, "Enter the Font Size","Font Size", JOptionPane.PLAIN_MESSAGE,null, null,"16");

			//If a string was returned, say so.
			if (sz != null) {
				//JOptionPane.showMessageDialog(null, "working"); 
				Font font = textarea.getFont();
			    textarea.setFont(new Font(font.getFontName(),Font.PLAIN,Integer.parseInt(sz)));
				//setLabel("Green eggs and... " + s + "!");
				 //* 
				 //* */
			}
			
			//JOptionPane.showMessageDialog(null, "working"); 
		}
		
		if(e.getSource().equals(ecu)) {
			textarea.cut();
			//JOptionPane.showMessageDialog(null, "working"); 
		}
		if(e.getSource().equals(eco)) {
			textarea.copy();
			//JOptionPane.showMessageDialog(null, "working"); 
		}
		if(e.getSource().equals(ep)) {
			textarea.paste();
			//JOptionPane.showMessageDialog(null, "working"); 
		}
		if(e.getSource().equals(esa)) {
			textarea.selectAll();
			//JOptionPane.showMessageDialog(null, "working");
			
		}
		if(e.getActionCommand().equals("Exit")) {
			System.exit(0);
		}
		
		if(e.getSource().equals(coc)) {
       	 JFileChooser fol = new JFileChooser();
       	 fol.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
       	 if(fol.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
       		 String pkg = fol.getSelectedFile().getName();
       		 String path = fol.getSelectedFile().getParent();
       		 String file = null;
       		 JFileChooser fil = new JFileChooser(new File(fol.getSelectedFile().getAbsolutePath()));
       		 
       		 if(fil.showDialog(fol, null)==JFileChooser.APPROVE_OPTION) {
       			 file = fil.getSelectedFile().getName();
       			 
       		 }
       		 
       		 //JFileChooser fl = new JFileChooser();
       		 //if(fl.showDialog(fol, "Run")==JFileChooser.APPROVE_OPTION){
				
				String comd = "javac ";
				comd = comd.concat("./");
				comd = comd.concat(pkg);
				comd = comd.concat("/");
			    comd = comd.concat(file);
			    comd = comd.concat(" & pause");
			    
			    /*System.out.println(comd);
			    System.out.println(path);
			    System.out.println(pkg);
			    */
			    try {
			           var processBuilder = new ProcessBuilder();
			           processBuilder.directory(new File(path));			
	                   processBuilder.command("cmd.exe","/c","start","cmd","/c",comd).directory(new File(path));	        
	                   processBuilder.start();	        
			   }catch(Exception ex) {
				      JOptionPane.showMessageDialog(null, ex.getMessage());
				      ex.printStackTrace();
			    }
			}
		}
		if(e.getSource().equals(cor)) {
			
			if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				//String path = fc.getSelectedFile().getAbsolutePath();
				String cmd = "javac ";
				String name = fc.getSelectedFile().getName();
			    cmd = cmd.concat(name);
			    cmd = cmd.concat(" & pause");
			    //System.out.println(cmd);
			try {
			var processBuilder = new ProcessBuilder();
	        processBuilder.command("cmd.exe","/c","start","cmd","/c",cmd).directory(new File(fc.getSelectedFile().getParent()));
	        
	        processBuilder.start();
	        
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
				ex.printStackTrace();
			}
			}
		}
         if(e.getSource().equals(rc)) {
        	 JFileChooser fol = new JFileChooser();
        	 fol.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        	 if(fol.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
        		 String pkg = fol.getSelectedFile().getName();
        		 String path = fol.getSelectedFile().getParent();
        		 String file = null;
        		 JFileChooser fil = new JFileChooser(new File(fol.getSelectedFile().getAbsolutePath()));
        		 
        		 if(fil.showDialog(fol, null)==JFileChooser.APPROVE_OPTION) {
        			 file = fil.getSelectedFile().getName();
        			 
        		 }
        		 
        		 //JFileChooser fl = new JFileChooser();
        		 //if(fl.showDialog(fol, "Run")==JFileChooser.APPROVE_OPTION){
				
				String comd = "java ";
				comd = comd.concat(pkg);
				comd = comd.concat(".");
				String b = file.substring(0, file.length()-5);
			    comd = comd.concat(b);
			    comd = comd.concat(" & pause");
			    
			    /*System.out.println(comd);
			    System.out.println(path);
			    System.out.println(pkg);
			    */
			    try {
			           var processBuilder = new ProcessBuilder();
			           processBuilder.directory(new File(path));			
	                   processBuilder.command("cmd.exe","/c","start","cmd","/c",comd).directory(new File(path));	        
	                   processBuilder.start();	        
			   }catch(Exception ex) {
				      JOptionPane.showMessageDialog(null, ex.getMessage());
				      ex.printStackTrace();
			    }
			}
		}
         
         if(e.getSource().equals(rr)) {
 			
 			if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
 				//String path = fc.getSelectedFile().getAbsolutePath();
 				String cmd = "java ";
 				String name = fc.getSelectedFile().getName();
 			    cmd = cmd.concat(name);
 			    cmd = cmd.concat(" & pause");
 			    //System.out.println(cmd);
 			try {
 			var processBuilder = new ProcessBuilder();
 	        processBuilder.command("cmd.exe","/c","start","cmd","/c",cmd).directory(new File(fc.getSelectedFile().getParent()));
 	        
 	        processBuilder.start();
 	        
 			}catch(Exception ex) {
 				JOptionPane.showMessageDialog(null, ex.getMessage());
 				ex.printStackTrace();
 			}
 			}
 		}
		
	}

}

