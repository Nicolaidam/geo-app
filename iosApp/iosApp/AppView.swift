import UIKit
import SwiftUI
import ComposeApp


struct AppView: View {
    
    @State var viewModel = LiveHomeViewModel(rawBaseUrl: "http://localhost:8080")

    var body: some View {

                 TabView {
                     
                     Tab("Uforsk", systemImage: "map") {
                         NavigationStack {
                             HomeView()
                         }
                     }
                     
                     Tab("Favoritter", systemImage: "heart") {
                         NavigationStack {
                             ExploreView()
                                 .navigationTitle("Favoritter")
                         }
                     }
                     
                     Tab("Profil", systemImage: "person") {
                         NavigationStack {
                             Text("Her kan man se: Navn, forrige kommentarer, ratings osv")
                                 .navigationTitle("Profil")
                         }
                     }
                     
                     Tab("Søg", systemImage: "magnifyingglass", role: .search) {
                         NavigationStack {
                             Text("Hello")
//                             appView(viewModel: viewModel)
//                             SearchFeatureView(store: searchStore)
                         }
                     }
                 }
             }
}

//#Preview("Location permission not determined") {
//    AppView(
//        viewModel: HomeViewModel(
//            geoApiClient: MockGeoApiClient(),
//            locationService: MockLocationService(
//                initialPermission: .notDetermined
//            )
//        )
//    )
//}
//
//#Preview("Location permission authorized") {
//    AppView(
//        viewModel: HomeViewModel(
//            geoApiClient: MockGeoApiClient(),
//            locationService: MockLocationService(
//                initialPermission: .authorized
//            )
//        )
//    )
//}
//
//#Preview("Location permission denied") {
//    AppView(
//        viewModel: HomeViewModel(
//            geoApiClient: MockGeoApiClient(),
//            locationService: MockLocationService(
//                initialPermission: .denied
//            )
//        )
//    )
//}



public extension Color {
    static let themeDarkGray = Color(#colorLiteral(red: 0.1529411765, green: 0.1647058824, blue: 0.2745098039, alpha: 1))
    static let themeDisabled = Color(#colorLiteral(red: 0.9359861503, green: 0.9446341087, blue: 0.9960437417, alpha: 1))
    static let themeHighligted = Color(#colorLiteral(red: 0.9359861503, green: 0.9446341087, blue: 0.9960437417, alpha: 1))
    static let themeSecondaryAction = Color(#colorLiteral(red: 0.9359861503, green: 0.9446341087, blue: 0.9960437417, alpha: 1))
    static let themeLightGray = Color(#colorLiteral(red: 0.921431005, green: 0.9214526415, blue: 0.9214410186, alpha: 1))
    static let themeLightGray2 = Color(#colorLiteral(red: 0.9122582078, green: 0.9092364907, blue: 0.9549568295, alpha: 1))
    static let themeWhite = Color(#colorLiteral(red: 0.9879724383, green: 1, blue: 1, alpha: 1))
    static let themeOrange = Color(#colorLiteral(red: 1, green: 0.7098039216, blue: 0.2980392157, alpha: 1))
    static let themeYellow = Color(#colorLiteral(red: 0.9725490196, green: 0.8392156863, blue: 0.4274509804, alpha: 1))
    static let themeRed = Color(#colorLiteral(red: 1, green: 0.4117647059, blue: 0.3803921569, alpha: 1))
    static let themeGreen = Color(#colorLiteral(red: 0.5490196078, green: 0.831372549, blue: 0.4941176471, alpha: 1))
    static let themePrimaryAction = Color(#colorLiteral(red: 0.1176470588, green: 0.662745098, blue: 0.4509803922, alpha: 1))
    static let themeBackground = Color(#colorLiteral(red: 0.937254902, green: 0.9450980392, blue: 0.9960784314, alpha: 1))
}
