import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    var body: some Scene {
        WindowGroup {
//            ContentViewSwiftUI()
            ContentViewSwiftUI(
//                viewModel: HomeViewModel.init(
//                    geoApi: ToiletAPIImpl(
//                        rawBaseUrl: "http://localhost:8080"
//                    ),
//                    locationService: MockLocationService(
//                        locationPermission: .notDetermined
//                    )
//                )
            )
//            ContentView()
        }
    }
}
