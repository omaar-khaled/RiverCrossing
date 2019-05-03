import Crossers.Farmer;
import Crossers.Plant;
import Crossers.Sheep;
import Crossers.Wolf;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class FilesParser {

    GameEngine Save;

    public void ReadSaveGame() {
            try {
                Farmer farmer = new Farmer();
                Wolf Wolf = new Wolf();
                Sheep Sheep = new Sheep();
                Plant Plant = new Plant();
                int temp;
                Save = GameEngine.getInstance();
                File Saves = new File("Saves.xml");
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                //doc is an object that we use to access the xml file i guess
                Document doc = dBuilder.parse(Saves);
                doc.getDocumentElement().normalize();
                NodeList LeftBank = doc.getElementsByTagName("LeftBank");
                NodeList RightBank = doc.getElementsByTagName("RightBank");
                NodeList OnBoat = doc.getElementsByTagName("OnBoat");
                NodeList Sails = doc.getElementsByTagName("Save");
                Node RightBankN = RightBank.item(0);
                Node LeftBankN = LeftBank.item(0);
                Node OnBoatN = OnBoat.item(0);
                Node SailsN = Sails.item(0);

                for (temp = 0; temp < LeftBankN.getChildNodes().getLength(); temp++) {


                    if (LeftBankN.getChildNodes().item(temp).getNodeName().equals("Wolf"))
                        Save.getCrossersOnLeftBank().add(Wolf);
                    if (LeftBankN.getChildNodes().item(temp).getNodeName().equals("Farmer"))
                        Save.getCrossersOnLeftBank().add(farmer);
                    if (LeftBankN.getChildNodes().item(temp).getNodeName().equals("Sheep"))
                        Save.getCrossersOnLeftBank().add(Sheep);
                    if (LeftBankN.getChildNodes().item(temp).getNodeName().equals("Plant"))
                        Save.getCrossersOnLeftBank().add(Plant);
                }


                for (temp = 0; temp < RightBankN.getChildNodes().getLength(); temp++) {


                    if (RightBankN.getChildNodes().item(temp).getNodeName().equals("Wolf"))
                        Save.getCrossersOnRightBank().add(Wolf);
                    if (RightBankN.getChildNodes().item(temp).getNodeName().equals("Farmer"))
                        Save.getCrossersOnRightBank().add(farmer);
                    if (RightBankN.getChildNodes().item(temp).getNodeName().equals("Sheep"))
                        Save.getCrossersOnRightBank().add(Sheep);
                    if (RightBankN.getChildNodes().item(temp).getNodeName().equals("Plant"))
                        Save.getCrossersOnRightBank().add(Plant);
                }

                for (temp = 0; temp < OnBoatN.getChildNodes().getLength(); temp++) {


                    if (OnBoatN.getChildNodes().item(temp).getNodeName().equals("Wolf"))
                        Save.getBoatRiders().add(Wolf);
                    if (OnBoatN.getChildNodes().item(temp).getNodeName().equals("Farmer"))
                        Save.getBoatRiders().add(farmer);
                    if (OnBoatN.getChildNodes().item(temp).getNodeName().equals("Sheep"))
                        Save.getBoatRiders().add(Sheep);
                    if (OnBoatN.getChildNodes().item(temp).getNodeName().equals("Plant"))
                        Save.getBoatRiders().add(Plant);
                }

                Save.setSails(Integer.parseInt(SailsN.getLastChild().getTextContent()));
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void WriteSaveGame() {
        try {
            Save = GameEngine.getInstance();

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            Document document = documentBuilder.newDocument();


            // root element
            Element root = document.createElement("Saves");
            document.appendChild(root);

            Element Saves = document.createElement("Save");
            root.appendChild(Saves);

            Element Left = document.createElement("LeftBank");
            Saves.appendChild(Left);

            Element Right = document.createElement("RightBank");
            Saves.appendChild(Right);

            Element OnBoat = document.createElement("OnBoat");
            Saves.appendChild(OnBoat);

            int temp;


            for (temp = 0; temp < Save.getCrossersOnLeftBank().size(); temp++) {
                System.out.println("left"+Save.getCrossersOnLeftBank().size());
                System.out.println("boat"+Save.getBoatRiders().size());
                System.out.println("right"+Save.getCrossersOnRightBank().size());

                if (Save.getCrossersOnLeftBank().get(temp) instanceof Farmer) {
                    Element Farmer = document.createElement("Farmer");
                    Farmer.appendChild(document.createTextNode("1"));
                    Left.appendChild(Farmer);
                    Element FarmerWeight = document.createElement("Weight");
                    FarmerWeight.appendChild(document.createTextNode(Double.toString(Save.getCrossersOnLeftBank().get(temp).getWeight())));
                    Farmer.appendChild(FarmerWeight);
                } else if (Save.getCrossersOnLeftBank().get(temp) instanceof Wolf) {
                    Element Wolf = document.createElement("Wolf");
                    Wolf.appendChild(document.createTextNode("1"));
                    Left.appendChild(Wolf);
                } else if (Save.getCrossersOnLeftBank().get(temp) instanceof Sheep) {
                    Element Sheep = document.createElement("Sheep");
                    Element IsSheep = document.createElement("IsSheep");
                    IsSheep.appendChild(document.createTextNode("1"));
                    Sheep.appendChild(IsSheep);
                    Element SheepWeight = document.createElement("Weight");
                    SheepWeight.appendChild(document.createTextNode(Double.toString(Save.getCrossersOnLeftBank().get(temp).getWeight())));
                    Sheep.appendChild(SheepWeight);
                    Left.appendChild(Sheep);
                } else if (Save.getCrossersOnLeftBank().get(temp) instanceof Plant) {
                    Element Plant = document.createElement("Plant");
                    Plant.appendChild(document.createTextNode("1"));
                    Left.appendChild(Plant);
                }
            }

            for (temp = 0; temp < Save.getCrossersOnRightBank().size(); temp++) {

                if (Save.getCrossersOnRightBank().get(temp) instanceof Farmer) {
                    Element Farmer = document.createElement("Farmer");
                    Element IsFarmer = document.createElement("IsFarmer");
                    IsFarmer.appendChild(document.createTextNode("1"));
                    Farmer.appendChild(IsFarmer);
                    Element FarmerWeight = document.createElement("Weight");
                    FarmerWeight.appendChild(document.createTextNode(Double.toString(Save.getCrossersOnRightBank().get(temp).getWeight())));
                    Farmer.appendChild(FarmerWeight);
                    Right.appendChild(Farmer);
                } else if (Save.getCrossersOnRightBank().get(temp) instanceof Wolf) {
                    Element Wolf = document.createElement("Wolf");
                    Wolf.appendChild(document.createTextNode("1"));
                    Right.appendChild(Wolf);
                } else if (Save.getCrossersOnRightBank().get(temp) instanceof Sheep) {
                    Element Sheep = document.createElement("Sheep");
                    Element IsSheep = document.createElement("IsSheep");
                    IsSheep.appendChild(document.createTextNode("1"));
                    Sheep.appendChild(IsSheep);
                    Element SheepWeight = document.createElement("Weight");
                    SheepWeight.appendChild(document.createTextNode(Double.toString(Save.getCrossersOnRightBank().get(temp).getWeight())));
                    Sheep.appendChild(SheepWeight);
                    OnBoat.appendChild(Sheep);
                } else if (Save.getCrossersOnRightBank().get(temp) instanceof Plant) {
                    Element Plant = document.createElement("Plant");
                    Plant.appendChild(document.createTextNode("1"));
                    Right.appendChild(Plant);
                }
            }

            for (temp = 0; temp < Save.getBoatRiders().size(); temp++) {

                if (Save.getBoatRiders().get(temp) instanceof Farmer) {
                    Element Farmer = document.createElement("Farmer");
                    Element IsFarmer = document.createElement("IsFarmer");
                    IsFarmer.appendChild(document.createTextNode("1"));
                    Farmer.appendChild(IsFarmer);
                    Element FarmerWeight = document.createElement("Weight");
                    FarmerWeight.appendChild(document.createTextNode(Double.toString(Save.getBoatRiders().get(temp).getWeight())));
                    Farmer.appendChild(FarmerWeight);
                    OnBoat.appendChild(Farmer);
                } else if (Save.getBoatRiders().get(temp) instanceof Wolf) {
                    Element Wolf = document.createElement("Wolf");
                    Wolf.appendChild(document.createTextNode("1"));
                    OnBoat.appendChild(Wolf);
                } else if (Save.getBoatRiders().get(temp) instanceof Sheep) {
                    Element Sheep = document.createElement("Sheep");
                    Element IsSheep = document.createElement("IsSheep");
                    IsSheep.appendChild(document.createTextNode("1"));
                    Sheep.appendChild(IsSheep);
                    Element SheepWeight = document.createElement("Weight");
                    SheepWeight.appendChild(document.createTextNode(Double.toString(Save.getBoatRiders().get(temp).getWeight())));
                    Sheep.appendChild(SheepWeight);
                    OnBoat.appendChild(Sheep);
                } else if (Save.getBoatRiders().get(temp) instanceof Plant) {
                    Element Plant = document.createElement("Plant");
                    Plant.appendChild(document.createTextNode("1"));
                    OnBoat.appendChild(Plant);
                }
            }

            Element Sails = document.createElement("Sails");
            Sails.appendChild(document.createTextNode(Integer.toString(Save.getNumberOfSails())));
            Saves.appendChild(Sails);

            Element boatPosition = document.createElement("BoatPostion");
            boatPosition.appendChild(document.createTextNode(Save.getBoatPosition()));
            Saves.appendChild(boatPosition);

            Element Level = document.createElement("Level");
            Level.appendChild(document.createTextNode(Integer.toString(Save.getLevelNumber())));
            Saves.appendChild(boatPosition);


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("Saves.xml"));

            // If you use
            // StreamResult result = new StreamResult(System.out);
            // the output will be pushed to the standard output ...
            // You can use that for debugging

            transformer.transform(domSource, streamResult);


        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();

        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}
