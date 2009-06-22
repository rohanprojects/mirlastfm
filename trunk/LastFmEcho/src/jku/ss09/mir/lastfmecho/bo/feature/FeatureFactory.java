package jku.ss09.mir.lastfmecho.bo.feature;

import jku.ss09.mir.lastfmecho.bo.MirArtist;

public class FeatureFactory {

	public static int FEATURE_TAGCLOUD_LASTFM = 1;
	public static int FEATURE_EPOCH= 2;
	public static int FEATURE_TAGCLOUD_GOOGLE= 3;
	
	
	
	public static Feature createFeatureForArtist(int featureId, MirArtist artist) {
		
		Feature feature  = null;
		if (featureId == FEATURE_TAGCLOUD_LASTFM) {	
			feature = new LastFMTagCloudFeature(artist);
		}else if (featureId == FEATURE_EPOCH) {
			feature = new EpochFeature(artist);
		}else if (featureId == FEATURE_TAGCLOUD_GOOGLE) {
			feature = new GoogleTagCloudFeature(artist);	
		}else
			feature = null;
		
		feature.calc();
		return feature;

	}
	
	
}
