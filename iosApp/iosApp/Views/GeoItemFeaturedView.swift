import SwiftUI
import ComposeApp

struct GeoItemFeaturedView: View {
    
    let onTap: () -> ()
    
    var body: some View {
        Image(decorative: "image")
            .resizable()
            .aspectRatio(contentMode: .fill)
            .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity)
            .clipped()
            .backgroundExtensionEffect()
            .overlay(alignment: .bottom) {
                VStack {
                    Text("Åbybro shelterplads")
                        .font(.largeTitle)
                        .fontWeight(.bold)
                        .foregroundColor(.white)
                    Text("4.2 km fra din lokation")
                        .font(.subheadline)
                        .fontWeight(.bold)
                        .foregroundColor(.white)
                    Button {
                        onTap()
                    } label: {
                        Text("Se mere")
                    }
                    
//                    .buttonStyle(.borderedProminent)
                    
                    .padding(.bottom, 24)
                }
                .padding([.bottom], 24)
            }
    }
}
