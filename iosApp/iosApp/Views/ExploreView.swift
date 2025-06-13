import SwiftUI
import MapKit
import ComposeApp

struct ExploreView: View {
    
    @State var mapCameraPositionForLandmark: MapCameraPosition = .automatic
    
    var body: some View {
        Map(position: $mapCameraPositionForLandmark, interactionModes: []) {
//            Marker(item: geoItemMapItem)
        }
    }
}
