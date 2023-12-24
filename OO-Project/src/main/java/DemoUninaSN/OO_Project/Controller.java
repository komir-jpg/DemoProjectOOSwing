package DemoUninaSN.OO_Project;

public class Controller {
	Demo LoginFrame;
	Frame2 LoginSuccessoframe;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Controller();
	}
	public Controller() {
		LoginFrame = new Demo(this);
		LoginFrame.setVisible(true);
	}
	
	public void setFrame2() {
		LoginSuccessoframe = new Frame2(this);
		LoginFrame.setVisible(false);
		LoginSuccessoframe.setVisible(true);
	}

}
