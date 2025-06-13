import MapKit
import ComposeApp
import SwiftUI

extension GeoItem: @retroactive Identifiable {}

extension GeoItem {
    var span: Double {
        1.0
    }
    var locationCoordinate: CLLocationCoordinate2D {
        CLLocationCoordinate2D(
            latitude: latitude,
            longitude: longitude
        )
    }
    
    var coordinateRegion: MKCoordinateRegion {
        MKCoordinateRegion(
            center: locationCoordinate,
            span: .init(
                latitudeDelta: span,
                longitudeDelta: span
            )
        )
    }
    
    var image: Image {
        switch self.type.code {
        case .shelter:
            Image("shelter")
        case .teltplads:
            Image("telt")
        default: Image("image")
        }
    }
}
