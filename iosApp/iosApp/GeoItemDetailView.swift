import ComposeApp
import SwiftUI

struct GeoItemDetailView: View {
    
    let geoItem: GeoItem
    
    public var body: some View {
        NavigationStack {
            ScrollView {
                VStack(alignment: .leading, spacing: 14 ) {
                                            infoSection
                    
                    //                    Text("Coordinate: \(viewModel.friluftsItem.coordinate)")
                    //                            .textSelection(.enabled)
                    //                            .tabViewStyle(PageTabViewStyle(indexDisplayMode: .always))
                    //                            .frame(height: 390)
                    //
                    if geoItem.photoUrls.isEmpty  {
                        Text("NOthing to show")
                    } else {
                        TabView {
                            ForEach(geoItem.photoUrls, id: \.self) { urlString in
                                
                                AsyncImage(url: URL(string: urlString)!) { image in
                                    image
                                        .resizable()
                                        .aspectRatio(contentMode: .fill)
                                        .frame(width: 360, height: 360)
                                        .clipped()
                                        .cornerRadius(20)
                                        .overlay(
                                            RoundedRectangle(cornerRadius: 4, style: .continuous)
                                                .stroke(Color.gray.opacity(0.2), lineWidth: 0.5)
                                        )
                                } placeholder: {
                                    ProgressView.init("Placehgolder")
                                }
                            }
                        }
                        .tabViewStyle(PageTabViewStyle(indexDisplayMode: .always))
                        .frame(height: 390)
                        .cornerRadius(20)
                    }
                    //                        }
                    //                        Spacer()
                    //
                    //                }
                    //            }
                    //            .onAppear {
                    //                Task {
                    //                    await viewModel.onAppear()
                    //                }
                    //            }
                        
                }
            }
            .padding()
            .navigationTitle(geoItem.name)
            .navigationBarTitleDisplayMode(.large)
            .frame(maxWidth: .infinity)
            .background(Color.themeBackground)
            .lineLimit(nil)
            //                        .foregroundColor(.cphPetrol)
            //                        .toolbar { toolbarContent }
        }
    }
}

extension GeoItemDetailView {
    
    @ViewBuilder private var infoSection: some View {
        if let distance = geoItem.distanceInKm {
            Text("Afstand: \(distance)km")
            //                .font(.cphSubheadline1)
        }
        Text("Kommune: ")
        //            .font(.cphSubheadline1)
        
        HStack {
            Button {
                //            urlTapped(link)
            } label: {
                Text("Besøg hjemmeside")
//                    .font(.cphLabel2, .cphActive)
            }
            Spacer()
//            Text("Sidst opdateret: \(lastFetchedDataString)")
//                .font(.cphLabel2)
//                .foregroundColor(.gray)
        }
        Divider()
        VStack(alignment: .leading, spacing: 4) {
            Text("Beskrivelse")
//                .font(.cphSubheadline1)
            VStack(spacing: 0) {
                Text(geoItem.description_  ?? "Ingen tilgængelig beskrivelse")
                    .frame(maxWidth: .infinity, alignment: .leading)
                    .padding(14)
//                    .font(.cphLabel2)
                    .background(Color.white.opacity(0.7))
                    .lineSpacing(3)
                HStack {
                    Text("friluftsItem.data.first!.waterQuality.stringDescription")
                        .padding(10)
//                        .font(.cphHeadline5, .white)
                }
                .frame(maxWidth: .infinity, maxHeight: 60)
//                .foregroundStyle(friluftsItem.data.first!.waterQuality.color.gradient.opacity(0.8))
                .cornerRadius(20)
                .background(Color.themePrimaryAction.gradient)
            }
            .cornerRadius(8)
        }
        
//        if let facilities = friluftsItem.facilities {
//            if !facilities.trimmingCharacters(in: .whitespaces).isEmpty {
//                VStack(alignment: .leading, spacing: 4) {
//                    Text("Faciliteter")
//                        .font(.cphSubheadline1)
//                    Text(facilities)
//                        .frame(maxWidth: .infinity, alignment: .leading)
//                        .padding(14)
//                        .font(.cphLabel2)
//                        .lineSpacing(3)
//                        .background(Color.white.opacity(0.7))
//                        .cornerRadius(8)
//                }
//            }
//        }
//        if !friluftsItem.comments.trimmingCharacters(in: .whitespaces).isEmpty {
//            Text("Kommentarer")
//                .font(.cphSubheadline1)
//            Text(friluftsItem.comments)
//                .frame(maxWidth: .infinity, alignment: .leading)
//                .padding(14)
//                .font(.cphLabel2, .white)
//                .lineSpacing(3)
//                .cornerRadius(8)
//        }
        Divider()
    }
    
    //    private func dataItemView(data: friluftsItem.Data) -> some View {
    //        VStack {
    //            Text(data.dateString)
    //                .font(.cphHeadline5)
    //                .padding(.bottom, 10)
    //            Grid {
    //                GridRow {
    //                    VStack(alignment: .center, spacing: 6) {
    //                        if let imageString = data.weatherType.imageString {
    //                            Image(systemName: imageString)
    //                                .renderingMode(.original)
    //                                .resizable()
    //                                .scaledToFit()
    //                                .frame(width: 70, height: 70)
    //                        } else {
    //                            Text("")
    //                                .frame(width: 70, height: 70)
    //                        }
    //                        Text("\(data.airTemperature)°C")
    //                            .font(.cphHeadline5)
    //                        Text("\(data.weatherType.description)\n nedbør: \(data.precipitation)mm")
    //                            .font(.cphLabel3, .cphGray)
    //                            .foregroundColor(.gray)
    //                            .lineLimit(nil)
    //                            .multilineTextAlignment(.center)
    //                    }
    //
    //                    VStack(alignment: .center, spacing: 6) {
    //                        Image(systemName: "drop.fill")
    //                            .resizable()
    //                            .scaledToFit()
    //                            .foregroundStyle(Color.cyan.gradient)
    //                            .frame(width: 70, height: 70)
    //                        Text("\(data.waterTemperature)°C")
    //                            .font(.cphHeadline5)
    //                        Text("Vand temperatur")
    //                            .font(.cphLabel3, .cphGray)
    //                    }
    //                }
    //                .frame(maxWidth: .infinity)
    //                GridRow {
    //                    VStack(spacing: 6) {
    //                        Image(systemName: "wind")
    //                            .renderingMode(.original)
    //                            .resizable()
    //                            .scaledToFit()
    //                            .frame(width: 70, height: 70)
    //                        Text("\(data.windSpeed)m/s")
    //                            .font(.cphHeadline5)
    //                        Text("Vind")
    //                            .font(.cphLabel3, .cphGray)
    //                    }
    //                    VStack(spacing: 6) {
    //                        Image(systemName: "alternatingcurrent")
    //                            .resizable()
    //                            .scaledToFit()
    //                            .foregroundStyle(Color.white)
    //                            .frame(width: 70, height: 70)
    //                        Text("\(data.currentSpeed)m/s")
    //                            .font(.cphHeadline5)
    //                        Text("Strøm")
    //                            .font(.cphLabel3, .cphGray)
    //                    }
    //                }
    //                .frame(maxWidth: .infinity)
    //            }
    //        }
    //        .lineLimit(nil)
    //        .multilineTextAlignment(.center)
    //        .frame(maxWidth: .infinity)
    //        .padding(14)
    //        .padding(.bottom, 60)
    //        .padding(.top, 30)
    //    }
    
    //    private var toolbarContent: some ToolbarContent {
    //        Group {
    //            ToolbarItem(placement: .navigationBarTrailing) {
    //                Button {
    ////                    onCloseButtonTapped()
    //                } label: {
    //                    toolbarImage(systemName: "xmark")
    //                }
    //            }
    ////            ToolbarItem(placement: .navigationBarLeading) {
    ////                Button {
    ////                } label: {
    ////                    Image(systemName: "heart.fill")
    ////                        .resizable()
    ////                        .frame(width: 24, height: 24)
    ////                        .foregroundStyle(Color.red.gradient)
    ////                }
    ////            }
    ////            if let link = friluftsItem.links.first {
    ////
    ////                ToolbarItem(placement: .bottomBar) {
    ////                    Button {
    ////                        let url = "http://maps.apple.com/maps?saddr=\(friluftsItem.latitude),\(friluftsItem.longitude)"
    ////                        urlTapped(url)
    ////                    } label: {
    ////                        HStack {
    ////                            Image(systemName: "map")
    ////                                .resizable()
    ////                                .frame(width: 20, height: 20)
    ////                            Text("Find vej")
    ////                                // .font(.bold, 14)
    ////                        }
    ////                        .foregroundColor(.cphActive)
    ////                    }
    ////                }
    ////            }
    //        }
    //    }
    //}
}

//struct LocationDetail_Previews: PreviewProvider {
//    static var previews: some View {
//        LocationDetail(
//            friluftsItem: .mock,
//            onCloseButtonTapped: {},
//            urlTapped: { _ in },
//            isLoading: .constant(false),
//            lastFetchedData: .constant(.distantFuture)
//        )
//        .registerFonts()
//    }
//}
