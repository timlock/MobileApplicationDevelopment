//
//  ContentView.swift
//  Shared
//
//  Created by Lock, Tim Niklas on 28.11.22.
//

import SwiftUI

struct ContentView: View {
    let diceNumber = Int.random(in: 1...6)
    var body: some View {
        VStack(alignment: .center, spacing: 0.0) {
            Image("dice-\(diceNumber)")
            Text( "You rolled a \(diceNumber)")
                .fontWeight(.bold)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
