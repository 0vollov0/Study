package test;

public class RequiredConvertInfo {
	private double longitude;
	private double latitude;
	private double pixelX;
	private double pixelY;

	public RequiredConvertInfo(double longitude, double latitude, double pixelX, double pixelY) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.pixelX = pixelX;
		this.pixelY = pixelY;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getPixelX() {
		return pixelX;
	}

	public void setPixelX(double pixelX) {
		this.pixelX = pixelX;
	}

	public double getPixelY() {
		return pixelY;
	}

	public void setPixelY(double pixelY) {
		this.pixelY = pixelY;
	}

	@Override
	public String toString() {
		return longitude + ", " + latitude + ", " + pixelX + ", " + pixelY;
	}

}
