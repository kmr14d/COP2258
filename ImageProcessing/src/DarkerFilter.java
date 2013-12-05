import java.awt.Color;

public class DarkerFilter extends ColorFilter{
	public Color filterOperation(Color c) {
		Color out = c.darker();
		return out;
	}
}
