import Foundation
import SwiftUI
import ComposeApp

/// A class the app uses to store and manage model data.
@Observable @MainActor
class ModelData {
    var homeViewModel: HomeViewModel
//    var path: NavigationPath = NavigationPath() {
//        didSet {
//            // Check if the person navigates away from a view that's showing the inspector.
//            if path.count < oldValue.count && isLandmarkInspectorPresented == true {
//                // Dismiss the inspector.
//                isLandmarkInspectorPresented = false
//            }
//        }
//    }
    var windowSize: CGSize = .zero
    var nearGeoItems: [GeoItem] = []
    
    init(homeViewModel: HomeViewModel) {
        self.homeViewModel = homeViewModel
    }
}


extension ModelData {
    static let mock = ModelData(
        homeViewModel: LiveHomeViewModel(rawBaseUrl: "http://localhost:8080")
    )
}
