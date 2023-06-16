package ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import control.EvolutionManager;
import creature.Organism;

public class EvolutionGUI extends JFrame {

	/**Obligatory serial version*/
	private static final long serialVersionUID = 1L;

	private JPanel p;

	private CardLayout cl;

	Random rng;

//	public static final ImageIcon PCW_ICON = new ImageIcon("images/pcw_icon.jpg");

	public static int plantCount = 100;
	
	private boolean goAFK;
	
	private AFKThread thread;

	public EvolutionGUI() {

		p = new JPanel();
		cl = new CardLayout();
		p.setLayout(cl);
		
		rng = new Random();
		
		//Window formatting
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    setSize(650, 350);
	    setTitle("Evolution Simulator");
//	    setIconImage(PCW_ICON.getImage());
	    
	    p.add(new IntroPanel(), "Intro");	    
	    cl.show(p, "Intro");
	    getContentPane().add(p, BorderLayout.CENTER);

	    setVisible(true);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		EvolutionGUI hg = new EvolutionGUI();
	}
	
	@SuppressWarnings("serial")
	private class IntroPanel extends JPanel {
		public IntroPanel() {
			
			setLayout(new BorderLayout());
			
			List<Organism> orgs = EvolutionManager.getInstance().getOrganisms();
			JPanel control = new JPanel(new GridLayout(1, 6));
			JPanel creatures = new JPanel(new GridLayout((orgs.size() / 10) + 1, 10));
			for (int q = 0; q < orgs.size(); q++) {
				Organism o = orgs.get(q);
				JButton selector = new JButton();
				selector.setBackground(new Color(Math.max(0, Math.min(255, o.getRedColor())),
						Math.max(0, Math.min(255, o.getGreenColor())),
						Math.max(0, Math.min(255, o.getBlueColor()))));
				selector.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, o.toString());
					}
				});
				creatures.add(selector);
			}
			JScrollPane creatureDisplay = new JScrollPane(creatures);
			JTextField numPlants = new JTextField("" + plantCount);

			JButton nextRound = new JButton("Do Round");
			nextRound.setBackground(Color.BLUE);
			nextRound.setForeground(Color.BLACK);
			nextRound.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (goAFK) {
						JOptionPane.showMessageDialog(null, "Cannot manually progress while AFK mode is running");
					} else {
						String input = numPlants.getText().trim();
						int num = 0;
						try {
							num = Integer.parseInt(input);
							EvolutionManager.getInstance().generatePlants(num);
							plantCount = num;
							EvolutionManager.getInstance().prepayAndSortVores();
							EvolutionManager.getInstance().herbivorePhase();
							EvolutionManager.getInstance().violentPhase();
							EvolutionManager.getInstance().carnivorePhase();
							EvolutionManager.getInstance().payHalfCost();
							EvolutionManager.getInstance().friendlyPhase();
							EvolutionManager.getInstance().killDead();
							EvolutionManager.getInstance().reproductionPhase();
							EvolutionManager.getInstance().killDead();
							EvolutionManager.getInstance().endRound();
						
							p.add(new IntroPanel(), "Intro");
					    	cl.show(p, "Intro");
							validate();
							repaint();
						} catch (Exception x) {
							JOptionPane.showMessageDialog(null, "Number of plants must be a non-negative integer");
						}
					}
				}
			});
			JButton afk = new JButton("AFK Mode");
			afk.setBackground(Color.GREEN);
			afk.setForeground(Color.BLACK);
			afk.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (goAFK) {
						JOptionPane.showMessageDialog(null, "AFK is already running");
					} else {
					goAFK = true;
					thread = new AFKThread(numPlants);
					thread.start();
					}
				}
			});
			JButton stopAfk = new JButton("Stop AFK");
			stopAfk.setBackground(Color.RED);
			stopAfk.setForeground(Color.BLACK);
			stopAfk.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (!goAFK) {
						JOptionPane.showMessageDialog(null, "AFK is not running");
					} else {
						goAFK = false;
						try {
							thread.join();
						} catch (InterruptedException e1) {
						}
					}
				}
			});
			control.add(new JLabel("Round: " + EvolutionManager.getInstance().getRound()));
			control.add(new JLabel("Pop. Size: " + orgs.size()));
			control.add(numPlants);
			control.add(nextRound);
			control.add(afk);
			control.add(stopAfk);
			
			add(control, BorderLayout.PAGE_END);
			add(creatureDisplay, BorderLayout.CENTER);
		}
	}
	
	private class AFKThread extends Thread {
		
		private JTextField numPlants;
		
		public AFKThread(JTextField plants) {
			numPlants = plants;
		}
		
		public void run() {
			while (goAFK) {
				String input = numPlants.getText().trim();
				int num = 0;
				try {
					num = Integer.parseInt(input);
					EvolutionManager.getInstance().generatePlants(num);
					plantCount = num;
					EvolutionManager.getInstance().prepayAndSortVores();
					EvolutionManager.getInstance().herbivorePhase();
					EvolutionManager.getInstance().violentPhase();
					EvolutionManager.getInstance().carnivorePhase();
					EvolutionManager.getInstance().payHalfCost();
					EvolutionManager.getInstance().friendlyPhase();
					EvolutionManager.getInstance().killDead();
					EvolutionManager.getInstance().reproductionPhase();
					EvolutionManager.getInstance().killDead();
					EvolutionManager.getInstance().endRound();
				
					p.add(new IntroPanel(), "Intro");
					cl.show(p, "Intro");
					validate();
					repaint();
				} catch (Exception x) {
					JOptionPane.showMessageDialog(null, x.getStackTrace().toString());
					JOptionPane.showMessageDialog(null, "Number of plants must be a non-negative integer");
				}
			}
		}
	}

}