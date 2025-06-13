import ComposeApp
import SwiftUI

struct HomeView: View {
    
    @Environment(ModelData.self) var modelData
    
    var viewModel: HomeViewModel {
        modelData.homeViewModel
    }
    
    var body: some View {
        Observing(viewModel.state) {
            ProgressView()
        } content: { state in
            ScrollView {
                LazyVStack(alignment: .leading, spacing: 6) {
                    switch onEnum(of: state.viewState) {
                    case .dataLoaded(let data):
                        GeoItemFeaturedView(onTap: {
                            self.viewModel.onTapItem(item: data.data.items.first!)
                        })
                            .flexibleHeaderContent()
                        NavigationLink {
                            ExploreView()
                        } label: {
                            VStack(alignment: .leading, spacing: 18) {
                                Text("Udforsk kort")
                                    .font(.system(size: 24, weight: .bold, design: .default))
                                Text("Opdag nye frilufts lokationer og planlæg din næste tur ude i det fri.")
                            }
                            .background {
                                Image(decorative: "image")
                                    .resizable()
                                    .aspectRatio(contentMode: .fill)
                                    .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity)
                                    .clipped()
                                    .backgroundExtensionEffect()
                                    .blur(radius: 20)
                            }
                        }
                        Text("I nærheden").font(.system(size: 24, weight: .bold, design: .default))
                            .padding(.leading, 24)
                        GeoItemsHorizontalListView(geoItemList: data.data.items)
                            .containerRelativeFrame(.vertical) { height, axis in
                                let proposedHeight = height * Constants.landmarkListPercentOfHeight
                                if proposedHeight > Constants.landmarkListMinimumHeight {
                                    return proposedHeight
                                }
                                return Constants.landmarkListMinimumHeight
                            }
                        Text("Favoritter").font(.system(size: 24, weight: .bold, design: .default))
                            .padding(.leading, 24)
                        GeoItemsHorizontalListView(geoItemList: data.data.items)
                            .containerRelativeFrame(.vertical) { height, axis in
                                let proposedHeight = height * Constants.landmarkListPercentOfHeight
                                if proposedHeight > Constants.landmarkListMinimumHeight {
                                    return proposedHeight
                                }
                                return Constants.landmarkListMinimumHeight
                            }
                    case .error(let errorMessage):
                        errorView(retryInFlight: state.fetchGeoItemsInFlight, errorMessage: "error")
                    case .loading:
                        loadingView
                    }
                    
                    
                }
                .frame(maxWidth: .infinity)
                .animation(.bouncy, value: viewModel.state.value)
            }
            .flexibleHeaderScrollView()
            .ignoresSafeArea(.keyboard)
            .ignoresSafeArea(edges: .top)
            .toolbar(removing: .title)
//            .navigationDestination(for: Landmark.self) { landmark in
//                LandmarkDetailView(landmark: landmark)
//            }
            .background(Color.themeBackground)
            .sheet(
                item: .init(
                    get: {
                        viewModel.state.value.presentDetailSheet
                    },
                    set: {
                        viewModel.itemDetailSheetChanged(item: $0)
                    }
                ),
                onDismiss: {
                    viewModel.itemDetailSheetChanged(item: nil)
                },
                content: { geoItem in
                    GeoItemDetailView(geoItem: geoItem)
                }
            )
        }
    }
    
    @ViewBuilder
    var loadingView: some View {
        ProgressView()
    }
    
    @ViewBuilder
    func errorView(retryInFlight: Bool, errorMessage: String) -> some View {
        Text(errorMessage)
        if retryInFlight {
            ProgressView()
        } else {
            Button {
                viewModel.retryButtonTap()
            } label: {
                Text("Try again")
            }
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

#Preview {
    @Previewable @State var modelData = ModelData.mock
    NavigationStack {
        HomeView()
            .environment(modelData)
    }
}
