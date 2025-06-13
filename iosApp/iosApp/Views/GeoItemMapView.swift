import SwiftUI
import MapKit
import ComposeApp

struct GeoItemMapView: View {
    
    let geoItemMapItem: MKMapItem
    let mapCameraPositionForLandmark: MapCameraPosition
    
    init(geoItem: GeoItem) {
        self.geoItemMapItem = MKMapItem(placemark: MKPlacemark(coordinate: geoItem.locationCoordinate))
        self.mapCameraPositionForLandmark = .region(geoItem.coordinateRegion)
    }
    
    
    var body: some View {
        Map(position: .constant(mapCameraPositionForLandmark), interactionModes: []) {
            Marker(item: geoItemMapItem)
        }
    }
}
