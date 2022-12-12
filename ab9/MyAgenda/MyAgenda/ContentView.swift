//
//  ContentView.swift
//  MyAgenda
//
//  Created by Lock, Tim Niklas on 12.12.22.
//

import SwiftUI

struct MyTask: Identifiable{
    let id = UUID()
    let name: String
    let category: String
}
struct AddTaskView: View {
    @State private var name: String = ""
    @State private var selectedCategory = 0
    var categoryTypes = ["work","box","idea","phone"]
    @Binding var showAddTaskView: Bool
    @Binding var tasks: [MyTask]
    var body: some View {
        VStack{
            Text("Neue Aufgabe").font(.largeTitle)
            TextField("Aufgabe: ",text: $name)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .border(Color.black).padding()
            Text("Kategorie: ")
            Picker("",selection: $selectedCategory) {
                ForEach(0 ..< categoryTypes.count) {
                    Text(self.categoryTypes[$0])
                }
            }.pickerStyle(SegmentedPickerStyle())
        }
        Button("Fertig"){
        self.showAddTaskView = false
        tasks.append(MyTask(name: name,
        category: categoryTypes[selectedCategory]))
        }
    }
}
struct ContentView: View {
    @State private var showAddTaskView = false
    @State private var tasks = [
        MyTask(name:"MAD P9 bearbeiten",category: "work"),
        MyTask(name:"Schoko-Nikolaus kaufen",category: "box"),
        MyTask(name:"Santa anrufen",category: "phone"),
        MyTask(name:"MAD Projekt anmelden",category: "idea") ]
    
    var body: some View {
        VStack {
            NavigationView {
                List {
                    ForEach(tasks) { (task) in
                        NavigationLink(destination:
                                        VStack{
                            Text(task.name)
                            Image(task.category) .resizable()
                                .frame(width: 50, height: 50)
                        }
                        ){
                            HStack {
                                Image(task.category).resizable().frame(width:
                                                                        50, height: 50)
                                Text(task.name)
                            }
                        }
                    }.onDelete(perform: { indexSet in
                        tasks.remove(atOffsets: indexSet) })
                    .onMove(perform: { indices, newOffset in
                        tasks.move(fromOffsets: indices, toOffset: newOffset) })
                }.navigationBarTitle("Aufgaben") .navigationBarItems(
                    leading: Button("Add"){
                        self.showAddTaskView.toggle()
                    }.sheet(isPresented:$showAddTaskView) {
                        AddTaskView(showAddTaskView: self.$showAddTaskView, tasks:self.$tasks)
                    },
                    trailing: EditButton())
            }
        }
        .padding()
    }
}
struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

