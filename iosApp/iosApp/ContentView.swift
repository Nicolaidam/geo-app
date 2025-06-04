import UIKit
import SwiftUI
import ComposeApp

//struct ComposeView: UIViewControllerRepresentable {
//    
//    func makeUIViewController(context: Context) -> UIViewController {
//        MainViewControllerKt.MainViewController()
//    }
//    
//    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
//}
//
//
//
//struct ContentView: View {
//    var body: some View {
//        ComposeView()
//            .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
//    }
//}

extension GeoItem: Identifiable {
    
}

struct ContentViewSwiftUI: View {
    
    @State var viewModel = HomeViewModel(
//        geoApi: MockToiletApi(),
        geoApiClient: GeoApiClientImpl(
            rawBaseUrl: "http://localhost:8089"
        ),
        locationService: locationService()
    )

    var body: some View {
        Observing(viewModel.state) {
            ProgressView()
        } content: { state in
            EmptyView()
            // NavigationStack {
            //     ScrollView {
            //         VStack(alignment: .leading, spacing: 6) {
            //             switch onEnum(of: state.viewState) {
            //             case .dataLoaded(let data):
            //                 nearestView(data.data.nearestSection)
            //                 Button {
            //
            //                 } label: {
            //                     VStack {
            //                         Text("Udforsk kortet")
            //                         Text("Opdag nye geoter på tværs af hele Danmark.")
            //                     }
            //                 }
            //                 ForEach(Array(data.data.geos.enumerated()), id: \.offset) { _, geo in
            //                     VStack {
            //                         Text(geo.name)
            //                         if let distance = geo.distanceInKm {
            //                             Text("\(distance) km")
            //                         }
            //                     }
            //                     .onTapGesture {
            //                         self.viewModel.onTapItem(item: geo)
            //                     }
            //                 }
            //
            //             case .error(let error):
            //                 Text(error.error)
            //                 if state.fetchToiletsInFlight {
            //                     ProgressView()
            //                 } else {
            //                     Button {
            //                         viewModel.retryButtonTap()
            //                     } label: {
            //                         Text("Try again")
            //                     }
            //                 }
            //             case .loading:
            //                 ProgressView()
            //             }
            //         }
            //         .padding(.horizontal, 14)
            //         .refreshable {
            //             viewModel.retryButtonTap()
            //
            //         }
            //         .frame(maxWidth: .infinity)
            //         .animation(.bouncy, value: viewModel.state.value)
            //     }
            //     .navigationTitle("Toilet appen")
            //     .background(Color.themeBackground)
            //     .sheet(
            //         item: .init(
            //             get: {
            //                 viewModel.state.value.presentDetailSheet
            //             },
            //             set: {
            //                 viewModel.itemDetailSheetChanged(item: $0)
            //             }
            //         ),
            //         onDismiss: {
            //             viewModel.itemDetailSheetChanged(item: nil)
            //         },
            //         content: { geoItem in
            //             GeoItemDetailView(geoItem: geoItem)
            //         }
            //     )
            // }
        }
    }
    
    @ViewBuilder
    private func nearestView(_ nearestSection: NearestSection) -> some View {
       switch onEnum(of: nearestSection) {
       case .getPermission:
           Button("Get permission") {
               viewModel.allowLocationPermissionButtonTap()
           }
       case .goToSettings:
           Button("Go to settings") {

           }
       case .nearest(let nearest):
           Text("Tættest på dig")
           VStack(alignment: .leading) {
               Text(nearest.item.name)
               if let distance = nearest.item.distanceInKm {
                   Text("\(distance) km")
               }
           }
           .onTapGesture {
               self.viewModel.onTapItem(item: nearest.item)
           }
           .padding(16)
           .frame(maxWidth: .infinity, alignment: .leading)
           .background(
               RoundedRectangle(cornerRadius: 18, style: .continuous)
                   .fill(Color.themeWhite)
           )
       }
   }
}

//#Preview("Location permission not determined") {
//    ContentViewSwiftUI(
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
//    ContentViewSwiftUI(
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
//    ContentViewSwiftUI(
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
