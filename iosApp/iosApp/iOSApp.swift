import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    @State private var modelData = ModelData(
        homeViewModel: LiveHomeViewModel(rawBaseUrl: "http://localhost:8080")
    )
    var body: some Scene {
        WindowGroup {
            AppView()
                .environment(modelData)
                .frame(minWidth: 375.0, minHeight: 600.0)
            // Keeps the current window's size for use in scrolling header calculations.
                .onGeometryChange(for: CGSize.self) { geometry in
                    geometry.size
                } action: {
                    modelData.windowSize = $0
                }
        }
    }
}
