package jku.ss09.mir.lastfmecho.bo.feature;

import jku.ss09.mir.lastfmecho.bo.MirArtist;

public class FeatureFactory {

	
	
	
	public static int FEATURE_TAGCLOUD = 1;
	public static int FEATURE_EPOCHE= 2;
	
	
	
	public static Feature createFeatureForArtist(int featureId, MirArtist artist) {
		
		Feature feature  = null;
		if (featureId == FEATURE_TAGCLOUD) {	
			feature = new TagCloudFeature(artist);
		} else if (featureId == FEATURE_EPOCHE) {
			feature =  null;
		}
		
		feature.calc();
		return feature;

	}
	
	
}
