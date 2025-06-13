import SwiftUI
import ComposeApp

/// A view that scrolls a list of landmarks horizontally.
struct GeoItemsHorizontalListView: View {
    let geoItemList: [GeoItem]
    
    var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            LazyHStack(spacing: Constants.standardPadding) {
                Spacer()
                    .frame(width: Constants.standardPadding)
                ForEach(geoItemList) { geoItem in
                    NavigationLink(value: geoItem) {
                        GeoListItemView(geoItem: geoItem)
                            .aspectRatio(Constants.landmarkListItemAspectRatio, contentMode: .fill)
                    }
                    .buttonStyle(.plain)
                }
            }
        }
    }
}

import SwiftUI

/// A view that shows a single landmark in a list.
struct GeoListItemView: View {
    let geoItem: GeoItem
    
    var body: some View {
        geoItem.image
            .resizable()
            .aspectRatio(contentMode: .fill)
            .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity)
            .overlay {
                ReadabilityRoundedRectangle()
            }
            .clipped()
            .cornerRadius(Constants.cornerRadius)
            .overlay(alignment: .bottom) {
                Text("landmark.name")
                    .font(.title3).fontWeight(.semibold)
                    .multilineTextAlignment(.center)
                    .foregroundColor(.white)
                    .padding(.bottom)
            }
        //            .contextMenu {
        //                ShareLink(item: landmark, preview: landmark.sharePreview)
        //                LandmarkFavoriteButton(landmark: landmark)
        //                LandmarkCollectionsMenu(landmark: landmark)
        //            }
    }
}

//#Preview {
//    let modelData = ModelData()
//    let previewLandmark = modelData.landmarksById[1001] ?? modelData.landmarks.first!
//    LandmarkListItemView(landmark: previewLandmark)
//        .frame(width: 252.0, height: 180.0)
//}


//
//#Preview {
//    let modelData = ModelData()
//
//    LandmarkHorizontalListView(landmarkList: modelData.landmarks)
//        .frame(height: 180.0)
//}

import SwiftUI

/// A view that adds a gradient over an image to improve legibility for a text overlay.
struct ReadabilityRoundedRectangle: View {
    var body: some View {
        RoundedRectangle(cornerRadius: Constants.cornerRadius)
            .foregroundStyle(.clear)
            .background(
                LinearGradient(colors: [.black.opacity(0.8), .clear], startPoint: .bottom, endPoint: .center)
            )
            .containerRelativeFrame(.vertical)
            .clipped()
    }
}

