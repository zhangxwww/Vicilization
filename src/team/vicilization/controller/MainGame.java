package team.vicilization.controller;


import team.vicilization.gameitem.*;
import team.vicilization.gameitem.building.BuildingType;
import team.vicilization.gameitem.city.City;
import team.vicilization.gameitem.unit.*;
import team.vicilization.gamemap.*;
import team.vicilization.mechanics.giant.*;
import team.vicilization.mechanics.science.*;
import team.vicilization.country.*;
import team.vicilization.util.Position;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.Timer;
import java.util.Random;


public class MainGame extends State {

    private int round;

    private MapArea mapArea;
    private UpperInfoArea upperInfoArea;
    private LowerInfoArea lowerInfoArea;

    private Vector<Country> countries;
    private Country currentPlayer;
    private Country enermy;

    private Vector<City> cities;
    private Vector<Unit> units;

    private static JButton nextRoundButton;
    private static JButton unitUpgradeButton;
    private static JButton unitMoveButton;
    private static JButton unitFightButton;
    private static JButton explorerBuildCityButton;
    private static JButton constructorHarvestButton;

    private static JComboBox<BuildingType> availableBuildings;
    private static JComboBox<UnitSubType> availableUnits;

    private boolean unitSeleted;
    private boolean unitMoving;
    private boolean unitAttacking;
    private Unit selectedUnit;

    private boolean citySelected;
    private City selectedCity;

    private boolean startGame[];

    private UnitSubType underProducingUnit;
    private BuildingType underProducingBuilding;

    private ImageIcon attackButtonIcon;
    private ImageIcon harvestButtonIcon;
    private ImageIcon moveButtonIcon;
    private ImageIcon buildCityButtonIcon;
    private ImageIcon upgradeButtonIcon;
    private ImageIcon nextRoundButtonIcon;

    private Vector<GiantName> scientists;
    private Vector<GiantName> economists;
    private Vector<GiantName> engineers;

    public MainGame(MainWindow mainWindow, CountryName[] countrys) {
        super(mainWindow);
        setNextState(StateType.Gameover);

        this.initIcons();
        this.initParams();
        this.initGiants();
        this.initMapArea();
        this.initButtons();
        this.initLowerInfoArea();
        this.initCountries(countrys);
        this.initUpperInfoArea();
        this.initComboxes();
        this.transView();
    }

    private void nextRound() {
        this.unselectCity();
        this.unselectUnit();
        this.mapArea.mapPanel.updateMap();

        if (judgeVictory()) {
            this.mainWindow.convertToNextState(currentPlayer);
        } else {
            round++;
            this.currentPlayer.endOfCurrentRound();
            this.enermy = this.currentPlayer;
            this.currentPlayer = this.countries
                    .elementAt(round % 2);
            this.currentPlayer.readyForNewRound(this.mapArea.getMap(), this.enermy);
            this.synchronizeCitiesAndUnits();
            this.upperInfoArea.update();
            this.transView();
            this.mapArea.mapPanel.updateMap();
            if (currentPlayer.getCities().size() > 0) {
                selectCity(currentPlayer.getCities().get(0));
            } else if (currentPlayer.getUnits().size() > 0) {
                selectUnit(currentPlayer.getUnits().get(0));
            }
        }
    }

    private void initIcons() {
        this.attackButtonIcon = new ImageIcon("./Resource/buttons/attack.png");
        this.harvestButtonIcon = new ImageIcon("./Resource/buttons/harvest.png");
        this.moveButtonIcon = new ImageIcon("./Resource/buttons/move.png");
        this.buildCityButtonIcon = new ImageIcon("./Resource/buttons/settle.png");
        this.upgradeButtonIcon = new ImageIcon("./Resource/buttons/upgrade.png");
        this.nextRoundButtonIcon = new ImageIcon("./Resource/buttons/next.png");
    }

    private void initParams() {
        this.round = 0;
        this.unitSeleted = false;
        this.unitAttacking = false;
        this.unitMoving = false;
        this.citySelected = false;
        this.selectedCity = null;
        this.selectedUnit = null;
        this.startGame = new boolean[2];
        this.startGame[0] = false;
        this.startGame[1] = false;
        this.cities = new Vector<City>();
        this.underProducingUnit = UnitSubType.NONE;
        this.underProducingBuilding = BuildingType.NONE;
    }

    private void initGiants() {
        Vector<GiantName> giants = new Vector<>(Arrays.asList(GiantName.values()));
        scientists = new Vector<>();
        economists = new Vector<>();
        engineers = new Vector<>();
        for (GiantName name : giants) {
            if (GiantConfig.GIANT_NAME_TO_TYPE.get(name) == GiantType.SCIENTIST) {
                scientists.add(name);
            } else if (GiantConfig.GIANT_NAME_TO_TYPE.get(name) == GiantType.ECONOMIST) {
                economists.add(name);
            } else {
                engineers.add(name);
            }
        }
    }

    private void initCountries(CountryName[] countrys) {
        this.countries = new Vector<>(2);
        this.units = new Vector<>();
        for (int i = 0; i < 2; i++) {
            Country country = new Country(countrys[i]);
            this.countries.add(country);
            this.initUnitsForOneCountry(country, i);
        }
        this.currentPlayer = this.countries.elementAt(0);
        this.enermy = this.countries.elementAt(1);
        this.selectUnit(currentPlayer.getUnits().get(0));
    }

    private void initUnitsForOneCountry(Country country, int order) {
        Random initPosition = new Random();
        while (true) {
            int xbias = order * GameMapConfig.MAP_WIDTH / 2;
            int ybias = order * GameMapConfig.MAP_HEIGHT / 2;
            int x = initPosition.nextInt(GameMapConfig.MAP_WIDTH / 2) + xbias;
            int y = initPosition.nextInt(GameMapConfig.MAP_HEIGHT / 2) + ybias;
            LandSquare landSquare = mapArea.at(x, y);
            if ((landSquare.getTerrainType() == TerrainType.PLAIN
                    || landSquare.getTerrainType() == TerrainType.HILL)
                    && (landSquare.getLandformType() == LandformType.GRASSLANDS
                    || landSquare.getLandformType() == LandformType.RAINFOREST
                    || landSquare.getLandformType() == LandformType.FOREST)
                    && (landSquare.getResourceType() == ResourceType.NONE)) {

                Position initPos = new Position(x, y);
                Unit explorer = new Explorer(initPos, country);
                country.addNewUnit(explorer);
                this.units.add(explorer);
                this.mapArea.addUnitInMap(explorer, initPos);
                break;
            }
        }
    }

    private void initComboxes() {
        ComboxItemListener listener = new ComboxItemListener();

        availableBuildings = new JComboBox<BuildingType>();
        availableUnits = new JComboBox<UnitSubType>();

        availableBuildings.setBounds(1440, 1000, 200, 40);
        availableUnits.setBounds(1220, 1000, 200, 40);

        availableBuildings.setFont(new Font("Consolas", Font.PLAIN, 20));
        availableUnits.setFont(new Font("Consolas", Font.PLAIN, 20));

        availableBuildings.addItemListener(listener);
        availableUnits.addItemListener(listener);
    }

    private void initButtons() {
        GameButtonsListener listener = new GameButtonsListener();

        nextRoundButton = new JButton(nextRoundButtonIcon);
        nextRoundButton.setBounds(1800, 940, 100, 100);
        nextRoundButton.addActionListener(listener);
        nextRoundButton.setBorderPainted(false);
        nextRoundButton.setContentAreaFilled(false);
        this.panel.add(nextRoundButton);

        unitUpgradeButton = new JButton(upgradeButtonIcon);
        unitUpgradeButton.setBounds(1220, 950, 36, 36);
        unitUpgradeButton.addActionListener(listener);
        unitUpgradeButton.setBorderPainted(false);
        unitUpgradeButton.setContentAreaFilled(false);

        unitMoveButton = new JButton(moveButtonIcon);
        unitMoveButton.setBounds(1260, 950, 36, 36);
        unitMoveButton.addActionListener(listener);
        unitMoveButton.setBorderPainted(false);
        unitMoveButton.setContentAreaFilled(false);

        unitFightButton = new JButton(attackButtonIcon);
        unitFightButton.setBounds(1300, 950, 36, 36);
        unitFightButton.addActionListener(listener);
        unitFightButton.setBorderPainted(false);
        unitFightButton.setContentAreaFilled(false);

        explorerBuildCityButton = new JButton(buildCityButtonIcon);
        explorerBuildCityButton.setBounds(1340, 950, 36, 36);
        explorerBuildCityButton.addActionListener(listener);
        explorerBuildCityButton.setBorderPainted(false);
        explorerBuildCityButton.setContentAreaFilled(false);

        constructorHarvestButton = new JButton(harvestButtonIcon);
        constructorHarvestButton.setBounds(1380, 950, 36, 36);
        constructorHarvestButton.addActionListener(listener);
        constructorHarvestButton.setBorderPainted(false);
        constructorHarvestButton.setContentAreaFilled(false);
    }

    private void initUpperInfoArea() {
        this.upperInfoArea = new UpperInfoArea();
        this.panel.add(upperInfoArea);
    }

    private void initLowerInfoArea() {
        this.lowerInfoArea = new LowerInfoArea();
        this.panel.add(lowerInfoArea);
    }

    private void initMapArea() {
        this.mapArea = new MapArea();
        this.panel.add(mapArea);
    }

    private void synchronizeCitiesAndUnits() {
        this.units.clear();
        this.cities.clear();
        for (Unit unit : currentPlayer.getUnits()) {
            units.add(unit);
        }
        for (Unit unit : enermy.getUnits()) {
            units.add(unit);
        }
        for (City city : currentPlayer.getCities()) {
            cities.add(city);
        }
        for (City city : enermy.getCities()) {
            cities.add(city);
        }
    }

    private boolean judgeVictory() {
        if (this.currentPlayer.judgeScienceVictory()) {
            // science victory
            return true;
        } else if (enermy.getCities().size() == 0
                && startGame[(round + 1) % 2]) {
            // war victory
            return true;
        } else if (round == 1000) {
            // time victory
            return true;
        } else {
            return false;
        }
    }

    private void selectUnit(Unit unit) {
        this.selectedCity = null;
        this.citySelected = false;
        this.unitSeleted = true;
        this.selectedUnit = unit;
        this.lowerInfoArea.showUnitInfo(unit);
        if (!selectedUnit.isMovedThisTurn()) { // 没有移动，显示移动按钮
            this.panel.add(unitMoveButton);
        } else {
            try {
                this.panel.remove(unitMoveButton);
            } catch (Exception e) {
            }
        }
        if (!selectedUnit.isAttackedThisTurn()
                && selectedUnit.getType() == UnitType.FIGHTER) {
            Vector<LandSquare> attackSquares = calculateAttackSquares();
            if (attackSquares.size() > 0) {
                this.panel.add(unitFightButton);
            } else {
                try {
                    this.panel.remove(unitFightButton);
                } catch (Exception e) {
                }
            }
        }
        if (selectedUnit.getType() == UnitType.FIGHTER
                && ((Fighter) selectedUnit).isUpgradable()) {
            this.panel.add(unitUpgradeButton);
        } else {
            try {
                this.panel.remove(unitUpgradeButton);
            } catch (Exception e) {
            }
        }
        if (selectedUnit.getSubType() == UnitSubType.EXPLORER) {
            LandSquare landSquare = this.mapArea.at(selectedUnit.getPosition());
            if (!currentPlayer.hasLandSquare(landSquare)
                    && !enermy.hasLandSquare(landSquare)) {
                this.panel.add(explorerBuildCityButton);
            } else {
                try {
                    this.panel.remove(explorerBuildCityButton);
                } catch (Exception e) {
                }
            }
        }
        if (selectedUnit.getSubType() == UnitSubType.CONSTRUCTOR) {
            LandformType landformType = this.mapArea.at(
                    selectedUnit.getPosition()).getLandformType();
            if (landformType == LandformType.FOREST
                    || landformType == LandformType.RAINFOREST
                    || landformType == LandformType.MARSH) {
                this.panel.add(constructorHarvestButton);
            } else {
                try {
                    this.panel.remove(constructorHarvestButton);
                } catch (Exception e) {
                }
            }
        }
        this.panel.repaint();
    }

    private void unselectUnit() {
        this.unitSeleted = false;
        this.unitMoving = false;
        this.unitAttacking = false;
        try {
            this.panel.remove(unitMoveButton);
        } catch (Exception e) {
        }
        try {
            this.panel.remove(unitFightButton);
        } catch (Exception e) {
        }
        try {
            this.panel.remove(unitUpgradeButton);
        } catch (Exception e) {
        }
        try {
            this.panel.remove(explorerBuildCityButton);
        } catch (Exception e) {
        }
        try {
            this.panel.remove(constructorHarvestButton);
        } catch (Exception e) {
        }
        this.panel.repaint();
        this.selectedUnit = null;
        this.lowerInfoArea.unshowUnitInfo();
    }

    private void prepareAttack() {
        this.unitAttacking = true;
        this.mapArea.mapPanel.updateMap();
    }

    private void unprepareAttack() {
        this.unitAttacking = false;
        this.panel.repaint();
    }

    private void buildCity() {
        this.startGame[round % 2] = true;
        Position pos = selectedUnit.getPosition();
        currentPlayer.deleteUnit(selectedUnit);
        City city = currentPlayer.buildNewCity(pos, mapArea.getMap(), enermy);
        this.cities.add(city);
        this.units.remove(selectedUnit);
        this.unselectUnit();
        this.selectCity(city);
        this.mapArea.mapPanel.updateMap();
    }

    private void selectCity(City city) {
        this.selectedUnit = null;
        this.unitSeleted = false;
        this.unitMoving = false;
        this.unitAttacking = false;
        this.citySelected = true;
        this.selectedCity = city;
        this.lowerInfoArea.showCityInfo(city);
        this.showSelectProduce();
        this.panel.repaint();
    }

    private void unselectCity() {
        this.citySelected = false;
        this.unshowSelectProduce();
        this.panel.repaint();
        this.selectedCity = null;
        this.lowerInfoArea.unshowCityInfo();
    }

    private void showSelectProduce() {
        if (!selectedCity.isProducing()) {
            this.getAllowedBuildings();
            this.getAllowedUnits();
            this.panel.add(availableBuildings);
            this.panel.add(availableUnits);
        }
    }

    private void unshowSelectProduce() {
        try {
            this.panel.remove(availableBuildings);
        } catch (Exception e) {
        }
        try {
            this.panel.remove(availableUnits);
        } catch (Exception e) {
        }
        this.clearAllowedBuildings();
        this.clearAllowedUnits();
    }

    private void harvest() {
        Constructor constructor = (Constructor) this.selectedUnit;
        constructor.reduceTimes();
        Position position = constructor.getPosition();
        LandSquare landSquare = this.mapArea.at(position);
        this.currentPlayer.harvestLandform(position, landSquare.getLandformType());
        landSquare.harvested();
        if (constructor.getTimes() == 0) {
            this.units.remove(selectedUnit);
        }
        this.mapArea.mapPanel.updateMap();
    }

    private void transView() {
        Position position;
        if (this.currentPlayer.getCities().size() != 0) {
            position = currentPlayer.getCities().get(0).getLocation();
            this.mapArea.transViewTo(position);
        } else if (this.currentPlayer.getUnits().size() != 0) {
            position = currentPlayer.getUnits().get(0).getPosition();
            this.mapArea.transViewTo(position);
        }
    }

    private void fight(Fightable fighter, Fightable fought) {
        Fighter attacker = (Fighter) fighter;
        Fighter defencer = (Fighter) fought;
        Position defencerPos = defencer.getPosition();
        int attack = attacker.getAttack();
        int defence = defencer.getDefence();
        if (((Fighter) fighter).getSubType() == UnitSubType.KNIGHT
                && ((Fighter) fought).getSubType() == UnitSubType.SPEARMAN) {
            defence += 10;
        } else if (((Fighter) fighter).getSubType() == UnitSubType.SPEARMAN
                && ((Fighter) fought).getSubType() == UnitSubType.KNIGHT) {
            attack += 10;
        } else if (((Fighter) fighter).getSubType() == UnitSubType.KNIGHT
                && (((Fighter) fought).getSubType() == UnitSubType.FOOTMAN
                || ((Fighter) fought).getSubType() == UnitSubType.SWORDSMAN)) {
            attack += 10;
        } else if ((((Fighter) fighter).getSubType() == UnitSubType.FOOTMAN
                || ((Fighter) fighter).getSubType() == UnitSubType.SWORDSMAN)
                && ((Fighter) fought).getSubType() == UnitSubType.KNIGHT) {
            defence += 10;
        } else if ((((Fighter) fighter).getSubType() == UnitSubType.FOOTMAN
                || ((Fighter) fighter).getSubType() == UnitSubType.SWORDSMAN)
                && ((Fighter) fought).getSubType() == UnitSubType.SPEARMAN) {
            attack += 10;
        } else if ((((Fighter) fighter).getSubType() == UnitSubType.SPEARMAN
                && (((Fighter) fought).getSubType() == UnitSubType.FOOTMAN
                || ((Fighter) fought).getSubType() == UnitSubType.SWORDSMAN))) {
            defence += 10;
        }
        defence += this.mapArea.at(defencerPos).getDefenceBuff();
        if (((Fighter) fighter).getSubType() != UnitSubType.ARCHER) {
            fighter.injure(defence);
        }
        fought.injure(attack);
        if (fighter.isDied()) {
            units.remove((Unit) fighter);
        }
        if (fought.isDied()) {
            units.remove((Unit) fought);
        }
        attacker.setAttackedThisTurn(true);
        this.unselectUnit();
    }

    private void fight(Fightable fighter, City city) {
        Fighter attacker = (Fighter) fighter;
        Position position = city.getLocation();
        int attack = fighter.getAttack();
        int defence = city.getDefence();
        if (((Fighter) fighter).getSubType() != UnitSubType.ARCHER) {
            fighter.injure(defence);
        }
        city.injure(attack);
        if (fighter.isDied()) {
            units.remove((Unit) fighter);
        }
        if (city.isDied()) {
            this.currentPlayer.occupyCity(city);
        }
        attacker.setAttackedThisTurn(true);
        this.unselectUnit();
    }

    private void recruitGiant(GiantName giant) {
        // TODO
    }

    private void getAllowedBuildings() {
        availableBuildings.removeAllItems();
        Vector<BuildingType> allowedBuilding = selectedCity.getAllowedBuildings();
        availableBuildings.addItem(BuildingType.NONE);
        for (BuildingType type : allowedBuilding) {
            availableBuildings.addItem(type);
        }
        availableBuildings.setSelectedItem(BuildingType.NONE);
    }

    private void getAllowedUnits() {
        availableUnits.removeAllItems();
        Vector<UnitSubType> allowedUnits = selectedCity.getAllowedUnits();
        availableUnits.addItem(UnitSubType.NONE);
        for (UnitSubType type : allowedUnits) {
            availableUnits.addItem(type);
        }
        availableUnits.setSelectedItem(UnitSubType.NONE);
    }

    private void clearAllowedBuildings() {
        availableBuildings.removeAllItems();
    }

    private void clearAllowedUnits() {
        availableUnits.removeAllItems();
    }

    private void selectProduction(BuildingType type) {
        selectedCity.produce(type);
        this.unshowSelectProduce();
        this.lowerInfoArea.updateCityInfo();
        this.panel.repaint();
    }

    private void selectProduction(UnitSubType type) {
        selectedCity.produce(type);
        this.unshowSelectProduce();
        this.lowerInfoArea.updateCityInfo();
        this.panel.repaint();
    }

    private void drawAccesseble() {
        Vector<LandSquare> squares = selectedUnit.
                getAvailableLocation(this.mapArea.getMap());
        for (Unit unit : units) {
            squares.remove(this.mapArea.getMap().
                    getSquare(unit.getPosition()));
        }
        for (City city : cities) {
            squares.remove(this.mapArea.getMap().
                    getSquare(city.getLocation()));
        }
        mapArea.drawAccessableSquares(squares);
    }

    private Vector<LandSquare> calculateAttackSquares() {
        Vector<LandSquare> squares = ((Fighter) selectedUnit).
                getAttackRange(this.mapArea.getMap());
        Vector<LandSquare> attackSquares = new Vector<>();
        for (Unit unit : enermy.getUnits()) {
            if (squares.contains(this.mapArea.at(unit.getPosition()))) {
                attackSquares.add(this.mapArea.at(unit.getPosition()));
            }
        }
        for (City city : enermy.getCities()) {
            if (squares.contains(this.mapArea.at(city.getLocation()))) {
                attackSquares.add(this.mapArea.at(city.getLocation()));
            }
        }
        return attackSquares;
    }

    private void unitUpgrade() {
        Position position = selectedUnit.getPosition();
        UnitSubType subType = selectedUnit.getSubType();
        this.unselectUnit();
        this.unprepareAttack();
        currentPlayer.deleteUnit(selectedUnit);
        this.units.remove(selectedUnit);
        if (subType == UnitSubType.FOOTMAN) {
            this.selectedUnit = new SwordsMan(position, currentPlayer);
            this.currentPlayer.addNewUnit(selectedUnit);
        }
        this.selectUnit(selectedUnit);
        this.synchronizeCitiesAndUnits();
        this.mapArea.mapPanel.updateMap();
    }

    private void drawAttackRange(Vector<LandSquare> squares) {
        this.mapArea.drawAttackSquares(squares);
    }

    private class ComboxItemListener implements ItemListener {
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() != ItemEvent.SELECTED) {
                return;
            }
            UnitSubType unitSubType = UnitSubType.NONE;
            BuildingType buildingType = BuildingType.NONE;
            if (event.getSource() == MainGame.availableUnits
                    && (unitSubType = (UnitSubType)
                    MainGame.availableUnits.getSelectedItem()) != UnitSubType.NONE) {
                selectProduction(unitSubType);
            } else if (event.getSource() == MainGame.availableBuildings
                    && (buildingType = (BuildingType)
                    MainGame.availableBuildings.getSelectedItem()) != BuildingType.NONE) {
                selectProduction(buildingType);
            }
        }
    }

    private class GameButtonsListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getSource() == MainGame.nextRoundButton) {
                nextRound();
            } else if (event.getSource() == MainGame.unitMoveButton) {
                drawAccesseble();
                unitMoving = true;
            } else if (event.getSource() == MainGame.unitFightButton) {
                prepareAttack();
            } else if (event.getSource() == MainGame.explorerBuildCityButton) {
                buildCity();
            } else if (event.getSource() == MainGame.constructorHarvestButton) {
                harvest();
            } else if (event.getSource() == MainGame.unitUpgradeButton) {
                unitUpgrade();
            }
        }
    }

    private class UpperInfoArea extends JPanel {
        // show flow value, resource count ... here
        private JLabel sciencePointInfo;
        private JLabel moneyInfo;
        private JLabel roundInfo;

        private JLabel sciencePointSymbolLabel;
        private JLabel moneySymbolLabel;
        private JLabel roundSymbolLabel;

        private JLabel countryName_1;
        private JLabel countryName_2;

        private ImageIcon scienceIcon;
        private ImageIcon moneyIcon;

        private JLabel scienceNameLabel;
        private JLabel scienceProgressLabel;

        private ImageIcon scientistIcon;
        private ImageIcon economistIcon;
        private ImageIcon engineerIcon;

        private JLabel scientistLabel;
        private JLabel economistLabel;
        private JLabel engineerLabel;

        private JLabel scientistNameLabel;
        private JLabel economistNameLabel;
        private JLabel engineerNameLabel;

        private JLabel scientistProgressLabel;
        private JLabel economistProgressLabel;
        private JLabel engineerProgressLabel;

        public UpperInfoArea() {
            super();
            this.setLayout(null);
            this.setOpaque(true);
            this.setBounds(20, 0, 1880, 50);
            this.initIcons();
            this.initLabels();
            this.update();
        }

        public void update() {
            this.sciencePointInfo.setText(
                    String.valueOf(currentPlayer.getFlowValue().getScience()));
            this.moneyInfo.setText(
                    String.valueOf(currentPlayer.getStockValue().getMoney()) + "(+"
                            + String.valueOf(currentPlayer.getFlowValue().getMoney()) + ")");

            this.roundInfo.setText(String.valueOf(round / 2 + 1));
            if (round % 2 == 0) {
                this.countryName_1.setFont(new Font("Consolas", Font.BOLD, 25));
                this.countryName_2.setFont(new Font("Consolas", Font.PLAIN, 25));
            } else {
                this.countryName_1.setFont(new Font("Consolas", Font.PLAIN, 25));
                this.countryName_2.setFont(new Font("Consolas", Font.BOLD, 25));
            }
            try {
                this.scienceNameLabel.setText(currentPlayer.getCurrentScience().toString());
                this.scienceProgressLabel.setText(currentPlayer.getStockValue().getScience()
                        + " / " + ScienceConfig.SCIENCE_COST.get(currentPlayer.getCurrentScience()));
            } catch (Exception e) {
                this.scienceNameLabel.setText("");
                this.scienceProgressLabel.setText(" 0 / 0");
            }
            if (scientists.size() > 0) {
                GiantName scientist = scientists.get(0);
                this.scientistNameLabel.setText(scientist.toString());
                int total = GiantConfig.GIANT_TYPE_COST
                        .get(GiantType.SCIENTIST).getScientistValue();
                int cur = currentPlayer.getStockValue().getScientistValue();
                this.scientistProgressLabel.setText(cur + " / " + total);
            } else {
                this.scientistNameLabel.setText("Nobody");
                this.scientistProgressLabel.setText("0 / 0");
            }
            if (economists.size() > 0) {
                GiantName economist = economists.get(0);
                this.economistNameLabel.setText(economist.toString());
                int total = GiantConfig.GIANT_TYPE_COST
                        .get(GiantType.ECONOMIST).getTraderValue();
                int cur = currentPlayer.getStockValue().getTraderValue();
                this.economistProgressLabel.setText(cur + " / " + total);
            } else {
                this.economistNameLabel.setText("Nobody");
                this.economistProgressLabel.setText("0 / 0");
            }
            if (engineers.size() > 0) {
                GiantName engineer = engineers.get(0);
                this.engineerNameLabel.setText(engineer.toString());
                int total = GiantConfig.GIANT_TYPE_COST
                        .get(GiantType.ENGINEER).getEngineerValue();
                int cur = currentPlayer.getStockValue().getEngineerValue();
                this.engineerProgressLabel.setText(cur + " / " + total);
            } else {
                this.engineerNameLabel.setText("Nobody");
                this.engineerProgressLabel.setText("0 / 0");
            }

            this.repaint();
        }

        private void initLabels() {
            this.sciencePointInfo = new JLabel();
            this.moneyInfo = new JLabel();
            this.roundInfo = new JLabel();

            this.sciencePointInfo.setBounds(60, 0, 80, 50);
            this.moneyInfo.setBounds(210, 0, 140, 50);
            this.roundInfo.setBounds(1840, 0, 40, 25);

            this.sciencePointInfo.setFont(new Font("Consolas", Font.PLAIN, 22));
            this.moneyInfo.setFont(new Font("Consolas", Font.PLAIN, 22));

            this.sciencePointSymbolLabel = new JLabel(scienceIcon);
            this.moneySymbolLabel = new JLabel(moneyIcon);
            this.roundSymbolLabel = new JLabel("Rounds");

            this.sciencePointSymbolLabel.setOpaque(true);
            this.moneySymbolLabel.setOpaque(true);

            this.sciencePointSymbolLabel.setBounds(0, 0, 50, 50);
            this.moneySymbolLabel.setBounds(150, 0, 50, 50);
            this.roundSymbolLabel.setBounds(1760, 0, 80, 25);

            this.roundSymbolLabel.setFont(new Font("Consolas", Font.BOLD, 20));
            this.roundInfo.setFont(new Font("Consolas", Font.BOLD, 20));

            this.countryName_1 = new JLabel(countries.get(0).getCountryName().toString());
            this.countryName_2 = new JLabel(countries.get(1).getCountryName().toString());

            this.countryName_1.setBounds(1600, 0, 100, 25);
            this.countryName_2.setBounds(1600, 25, 100, 25);

            this.countryName_1.setFont(new Font("Consolas", Font.PLAIN, 25));
            this.countryName_2.setFont(new Font("Consolas", Font.PLAIN, 25));

            this.countryName_1.setOpaque(true);
            this.countryName_2.setOpaque(true);

            this.countryName_1.setForeground(CountryConfig.COLOR_OF_COUNTRY.
                    get(countries.get(0).getCountryName()));
            this.countryName_2.setForeground(CountryConfig.COLOR_OF_COUNTRY.
                    get(countries.get(1).getCountryName()));

            this.scienceNameLabel = new JLabel();
            this.scienceProgressLabel = new JLabel();

            this.scienceNameLabel.setBounds(400, 0, 200, 30);
            this.scienceProgressLabel.setBounds(400, 30, 200, 20);

            this.scienceNameLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
            this.scienceProgressLabel.setFont(new Font("Consolas", Font.PLAIN, 18));

            this.scientistLabel = new JLabel(scientistIcon);
            this.economistLabel = new JLabel(economistIcon);
            this.engineerLabel = new JLabel(engineerIcon);

            this.scientistNameLabel = new JLabel();
            this.economistNameLabel = new JLabel();
            this.engineerNameLabel = new JLabel();

            this.scientistProgressLabel = new JLabel();
            this.economistProgressLabel = new JLabel();
            this.engineerProgressLabel = new JLabel();

            this.scientistLabel.setBounds(650, 0, 50, 50);
            this.economistLabel.setBounds(930, 0, 50, 50);
            this.engineerLabel.setBounds(1210, 0, 50, 50);

            this.scientistNameLabel.setBounds(710, 0, 210, 30);
            this.economistNameLabel.setBounds(990, 0, 210, 30);
            this.engineerNameLabel.setBounds(1270, 0, 210, 30);

            this.scientistNameLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
            this.economistNameLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
            this.engineerNameLabel.setFont(new Font("Consolas", Font.PLAIN, 20));

            this.scientistProgressLabel.setBounds(710, 30, 200, 20);
            this.economistProgressLabel.setBounds(990, 30, 200, 20);
            this.engineerProgressLabel.setBounds(1270, 30, 200, 20);

            this.scientistProgressLabel.setFont(new Font("Consolas", Font.PLAIN, 18));
            this.economistProgressLabel.setFont(new Font("Consolas", Font.PLAIN, 18));
            this.engineerProgressLabel.setFont(new Font("Consolas", Font.PLAIN, 18));

            this.add(sciencePointSymbolLabel);
            this.add(moneySymbolLabel);
            this.add(roundSymbolLabel);
            this.add(sciencePointInfo);
            this.add(moneyInfo);
            this.add(roundInfo);
            this.add(countryName_1);
            this.add(countryName_2);
            this.add(scienceNameLabel);
            this.add(scienceProgressLabel);
            this.add(scientistLabel);
            this.add(economistLabel);
            this.add(engineerLabel);
            this.add(scientistNameLabel);
            this.add(economistNameLabel);
            this.add(engineerNameLabel);
            this.add(scientistProgressLabel);
            this.add(economistProgressLabel);
            this.add(engineerProgressLabel);
        }

        private void initIcons() {
            this.scienceIcon = new ImageIcon("./Resource/info/science.png");
            this.moneyIcon = new ImageIcon("./Resource/info/money.png");
            this.scientistIcon = new ImageIcon("./Resource/info/scientist.png");
            this.economistIcon = new ImageIcon("./Resource/info/economist.png");
            this.engineerIcon = new ImageIcon("./Resource/info/engineer.png");
        }
    }

    private class LowerInfoArea extends JPanel {
        // show selected unit / city info
        private JLabel unitTypeInfo;

        private JLabel healthInfo;
        private JLabel attackInfo;
        private JLabel defenceInfo;
        private JLabel movabilityOrPopulationInfo;

        private JLabel healthLabel;
        private JLabel attackLabel;
        private JLabel defenceLabel;
        private JLabel movabilityOrPopulationLabel;

        private ImageIcon healthIcon;
        private ImageIcon attackIcon;
        private ImageIcon defenceIcon;
        private ImageIcon movabilityIcon;

        private JLabel cityNameInfo;
        private JLabel cityFoodInfo;
        private JLabel cityProductivityInfo;
        private JLabel cityMoneyInfo;
        private JLabel cityScienceInfo;

        private JLabel cityFoodLabel;
        private JLabel cityProductivityLabel;
        private JLabel cityMoneyLabel;
        private JLabel cityScienceLabel;

        private ImageIcon foodIcon;
        private ImageIcon producticityIcon;
        private ImageIcon moneyIcon;
        private ImageIcon scienceIcon;

        private JLabel producingLabel;
        private JLabel producingItemLabel;
        private JLabel progressLabel;

        private ImageIcon populationIcon;
        private ImageIcon productionIcon;

        public LowerInfoArea() {
            super();
            this.setBounds(470, 940, 710, 100);
            this.setLayout(null);
            this.initIcons();
            this.initLabels();

            this.setOpaque(true);
        }

        public void showUnitInfo(Unit unit) {
            this.unitTypeInfo.setText(unit.getSubType().toString());
            this.attackInfo.setText(String.valueOf(unit.getUnitInfo().getAttack()));
            this.defenceInfo.setText(String.valueOf(unit.getUnitInfo().getDefence()));
            this.healthInfo.setText(String.valueOf(unit.getHealth()));
            this.movabilityOrPopulationLabel.setIcon(movabilityIcon);
            this.movabilityOrPopulationInfo.setText(String.valueOf(unit.getMobility()));
        }

        public void unshowUnitInfo() {
            this.unitTypeInfo.setText("");
            this.attackInfo.setText("");
            this.defenceInfo.setText("");
            this.healthInfo.setText("");
            this.movabilityOrPopulationInfo.setText("");
        }

        public void updateUnitInfo() {
            this.showUnitInfo(selectedUnit);
            this.repaint();
        }

        public void showCityInfo(City city) {
            this.attackInfo.setText(String.valueOf(city.getAttack()));
            this.defenceInfo.setText(String.valueOf(city.getDefence()));
            this.healthInfo.setText(String.valueOf(city.getHealth()));
            this.movabilityOrPopulationInfo.setText(String.valueOf(city.getPopulation()));
            this.movabilityOrPopulationLabel.setIcon(populationIcon);

            this.cityNameInfo.setText(city.getCityName().toString());
            this.cityFoodInfo.setText(String.valueOf(city.getFlowValue().getFood()));
            this.cityProductivityInfo.setText(String.valueOf(city.getFlowValue().getProductivity()));
            this.cityMoneyInfo.setText(String.valueOf(city.getFlowValue().getMoney()));
            this.cityScienceInfo.setText(String.valueOf(city.getFlowValue().getScience()));

            UnitSubType unitSubType = city.getProducingUnit();
            BuildingType buildingType = city.getProducingBuilding();
            String producing = "None";
            int total = 0;
            if (unitSubType != UnitSubType.NONE) {
                producing = unitSubType.toString();
                total = GameItemConfig.UNIT_PRODUCTIVITY_COST.get(unitSubType);
            } else if (buildingType != BuildingType.NONE) {
                producing = buildingType.toString();
                total = GameItemConfig.BUILDING_PRODUCTIVITY_COST.get(buildingType);
            }
            int progress = city.getStockValue().getProductivity();
            this.producingItemLabel.setText(producing);
            this.progressLabel.setText(progress + " / " + total);
        }

        public void updateCityInfo() {
            this.showCityInfo(selectedCity);
            this.repaint();
        }

        public void unshowCityInfo() {
            this.attackInfo.setText("");
            this.defenceInfo.setText("");
            this.healthInfo.setText("");

            this.cityNameInfo.setText("");
            this.cityFoodInfo.setText("");
            this.cityProductivityInfo.setText("");
            this.cityMoneyInfo.setText("");
            this.cityScienceInfo.setText("");

            this.producingItemLabel.setText("");
            this.progressLabel.setText("");
        }

        private void initIcons() {
            this.healthIcon = new ImageIcon("./Resource/unitinfo/health.png");
            this.attackIcon = new ImageIcon("./Resource/unitinfo/attack.png");
            this.defenceIcon = new ImageIcon("./Resource/unitinfo/defence.png");
            this.movabilityIcon = new ImageIcon("./Resource/unitinfo/movability.png");
            this.foodIcon = new ImageIcon("./Resource/info/food.png");
            this.producticityIcon = new ImageIcon("./Resource/info/productivity.png");
            this.moneyIcon = new ImageIcon("./Resource/info/money.png");
            this.scienceIcon = new ImageIcon("./Resource/info/science.png");
            this.populationIcon = new ImageIcon("./Resource/info/population.png");
            this.productionIcon = new ImageIcon("./Resource/info/producing.png");
        }

        private void initLabels() {
            this.attackLabel = new JLabel(attackIcon);
            this.defenceLabel = new JLabel(defenceIcon);
            this.healthLabel = new JLabel(healthIcon);
            this.movabilityOrPopulationLabel = new JLabel();

            this.attackLabel.setBounds(0, 20, 40, 40);
            this.defenceLabel.setBounds(0, 60, 40, 40);
            this.healthLabel.setBounds(100, 20, 40, 40);
            this.movabilityOrPopulationLabel.setBounds(100, 60, 40, 40);

            this.unitTypeInfo = new JLabel();
            this.healthInfo = new JLabel();
            this.attackInfo = new JLabel();
            this.defenceInfo = new JLabel();
            this.movabilityOrPopulationInfo = new JLabel();

            this.unitTypeInfo.setBounds(0, 0, 190, 20);
            this.attackInfo.setBounds(50, 20, 40, 40);
            this.defenceInfo.setBounds(50, 60, 40, 40);
            this.healthInfo.setBounds(150, 20, 40, 40);
            this.movabilityOrPopulationInfo.setBounds(150, 60, 40, 40);

            this.cityNameInfo = new JLabel();
            this.cityFoodInfo = new JLabel();
            this.cityProductivityInfo = new JLabel();
            this.cityMoneyInfo = new JLabel();
            this.cityScienceInfo = new JLabel();

            this.cityNameInfo.setBounds(250, 0, 190, 20);
            this.cityFoodInfo.setBounds(300, 20, 40, 40);
            this.cityProductivityInfo.setBounds(300, 60, 40, 40);
            this.cityMoneyInfo.setBounds(400, 20, 40, 40);
            this.cityScienceInfo.setBounds(400, 60, 40, 40);

            this.cityFoodLabel = new JLabel(foodIcon);
            this.cityProductivityLabel = new JLabel(producticityIcon);
            this.cityMoneyLabel = new JLabel(moneyIcon);
            this.cityScienceLabel = new JLabel(scienceIcon);

            this.cityFoodLabel.setBounds(250, 20, 40, 40);
            this.cityProductivityLabel.setBounds(250, 60, 40, 40);
            this.cityMoneyLabel.setBounds(350, 20, 40, 40);
            this.cityScienceLabel.setBounds(350, 60, 40, 40);

            this.producingLabel = new JLabel(productionIcon);
            this.producingItemLabel = new JLabel();
            this.progressLabel = new JLabel();

            this.producingLabel.setBounds(450, 20, 50, 50);
            this.producingItemLabel.setBounds(510, 20, 200, 30);
            this.progressLabel.setBounds(510, 50, 200, 20);

            this.unitTypeInfo.setFont(new Font("Consolas", Font.BOLD, 20));
            this.attackInfo.setFont(new Font("Consolas", Font.PLAIN, 18));
            this.defenceInfo.setFont(new Font("Consolas", Font.PLAIN, 18));
            this.healthInfo.setFont(new Font("Consolas", Font.PLAIN, 18));
            this.movabilityOrPopulationInfo.setFont(new Font("Consolas", Font.PLAIN, 18));

            this.cityNameInfo.setFont(new Font("Consolas", Font.BOLD, 20));
            this.cityFoodInfo.setFont(new Font("Consolas", Font.PLAIN, 18));
            this.cityProductivityInfo.setFont(new Font("Consolas", Font.PLAIN, 18));
            this.cityMoneyInfo.setFont(new Font("Consolas", Font.PLAIN, 18));
            this.cityScienceInfo.setFont(new Font("Consolas", Font.PLAIN, 18));

            this.producingItemLabel.setFont(new Font("Consolas", Font.PLAIN, 20));
            this.progressLabel.setFont(new Font("Consolas", Font.PLAIN, 15));

            this.add(attackLabel);
            this.add(defenceLabel);
            this.add(healthLabel);
            this.add(movabilityOrPopulationLabel);

            this.add(unitTypeInfo);
            this.add(healthInfo);
            this.add(attackInfo);
            this.add(defenceInfo);
            this.add(movabilityOrPopulationInfo);

            this.add(cityNameInfo);
            this.add(cityFoodInfo);
            this.add(cityProductivityInfo);
            this.add(cityMoneyInfo);
            this.add(cityScienceInfo);

            this.add(cityFoodLabel);
            this.add(cityProductivityLabel);
            this.add(cityMoneyLabel);
            this.add(cityScienceLabel);

            this.add(producingLabel);
            this.add(producingItemLabel);
            this.add(progressLabel);
        }
    }

    private class MapArea extends JPanel {

        private MapPanel mapPanel;

        public MapArea() {
            super();

            this.setBounds(20, 50, 1880, 890);
            this.setBackground(Color.BLUE);
            this.setLayout(null);

            mapPanel = new MapPanel();
            this.add(mapPanel);
        }

        public void addUnitInMap(Unit unit, Position position) {
            this.mapPanel.addUnit(unit, position);
        }

        public LandSquare at(int x, int y) {
            return mapPanel.map.getSquare(x, y);
        }

        public LandSquare at(Position position) {
            return mapPanel.map.getSquare(position);
        }

        public void drawAccessableSquares(Vector<LandSquare> squares) {
            this.mapPanel.drawAccessableSquares(squares);
        }

        public void drawCityTerritory() {
            this.mapPanel.drawTerritory();
        }

        public void drawAttackSquares(Vector<LandSquare> squares) {
            this.mapPanel.drawAttackSquares(squares);
        }

        public void transViewTo(Position position) {
            int x = position.getX() * 50;
            int y = position.getY() * 50;
            int deltax = 940 - x;
            int deltay = 445 - y;
            this.mapPanel.setLocation(deltax, deltay);
        }

        public GameMap getMap() {
            return this.mapPanel.map;
        }

        private class MapPanel extends JPanel {
            private Position bias;
            private GameMap map;

            private ImageIcon hill_desert_icon;
            private ImageIcon hill_frozenground_icon;
            private ImageIcon hills_forest_icon;
            private ImageIcon hills_grasslands_icon;
            private ImageIcon hills_rainforest_icon;
            private ImageIcon lake_icon;
            private ImageIcon plain_desert_icon;
            private ImageIcon plain_forest_icon;
            private ImageIcon plain_frozenground_icon;
            private ImageIcon plain_grasslands_icon;
            private ImageIcon plain_marsh_icon;
            private ImageIcon plain_rainforest_icon;
            private ImageIcon ridge_icon;
            private ImageIcon river_col_icon;
            private ImageIcon river_ne_icon;
            private ImageIcon river_nw_icon;
            private ImageIcon river_row_icon;
            private ImageIcon river_se_icon;
            private ImageIcon river_sw_icon;

            private ImageIcon constructor_icon;
            private ImageIcon explorer_icon;
            private ImageIcon footman_icon;
            private ImageIcon archer_icon;
            private ImageIcon knight_icon;
            private ImageIcon scout_icon;
            private ImageIcon spearman_icon;
            private ImageIcon swordsman_icon;

            private ImageIcon cityIcon;

            Vector<LandSquare> accessableSquares;
            Vector<LandSquare> attackSquares;

            public MapPanel() {
                super();
                this.map = new GameMap();
                this.setLayout(null);

                this.initIcons();
                this.setBounds(0, 0,
                        GameMapConfig.MAP_WIDTH * 50,
                        GameMapConfig.MAP_HEIGHT * 50);

                MapMouseEventListener dragScreen = new MapMouseEventListener();
                this.addMouseListener(dragScreen);
                this.addMouseMotionListener(dragScreen);

                this.initSquares();
                this.drawMapWithoutUnits();
                this.repaint();
            }

            private void initIcons() {
                this.hill_desert_icon = new ImageIcon("./Resource/terrain/hill_desert.png");
                this.hill_frozenground_icon = new ImageIcon("./Resource/terrain/hill_frozenground.png");
                this.hills_forest_icon = new ImageIcon("./Resource/terrain/hills_forest.png");
                this.hills_grasslands_icon = new ImageIcon("./Resource/terrain/hills_grasslands.png");
                this.hills_rainforest_icon = new ImageIcon("./Resource/terrain/hills_rainforest.png");
                this.lake_icon = new ImageIcon("./Resource/terrain/lake.png");
                this.plain_desert_icon = new ImageIcon("./Resource/terrain/plain_desert.png");
                this.plain_forest_icon = new ImageIcon("./Resource/terrain/plain_forest.png");
                this.plain_frozenground_icon = new ImageIcon("./Resource/terrain/plain_frozenground.png");
                this.plain_grasslands_icon = new ImageIcon("./Resource/terrain/plain_grasslands.png");
                this.plain_marsh_icon = new ImageIcon("./Resource/terrain/plain_marsh.png");
                this.plain_rainforest_icon = new ImageIcon("./Resource/terrain/plain_rainforest.png");
                this.ridge_icon = new ImageIcon("./Resource/terrain/ridge.png");
                this.river_col_icon = new ImageIcon("./Resource/terrain/river_col.png");
                this.river_ne_icon = new ImageIcon("./Resource/terrain/river_ne.png");
                this.river_nw_icon = new ImageIcon("./Resource/terrain/river_nw.png");
                this.river_row_icon = new ImageIcon("./Resource/terrain/river_row.png");
                this.river_se_icon = new ImageIcon("./Resource/terrain/river_se.png");
                this.river_sw_icon = new ImageIcon("./Resource/terrain/river_sw.png");

                this.constructor_icon = new ImageIcon("./Resource/unit/constructor.png");
                this.explorer_icon = new ImageIcon("./Resource/unit/explorer.png");
                this.footman_icon = new ImageIcon("./Resource/unit/footman.png");
                this.archer_icon = new ImageIcon("./Resource/unit/archer.png");
                this.knight_icon = new ImageIcon("./Resource/unit/knight.png");
                this.scout_icon = new ImageIcon("./Resource/unit/scout.png");
                this.spearman_icon = new ImageIcon("./Resource/unit/spearman.png");
                this.swordsman_icon = new ImageIcon("./Resource/unit/swordsman.png");

                this.cityIcon = new ImageIcon("./Resource/city/city.png");
            }

            private void initSquares() {
                for (int i = 0; i < GameMapConfig.MAP_HEIGHT; i++) {
                    for (int j = 0; j < GameMapConfig.MAP_WIDTH; j++) {
                        JLabel square = new JLabel();
                        square.setOpaque(true);
                        square.setBounds(
                                j * 50, i * 50,
                                50, 50);
                        this.add(square);
                    }
                }
            }

            public void drawAccessableSquares(Vector<LandSquare> squares) {
                accessableSquares = squares;
                for (LandSquare landSquare : accessableSquares) {
                    Position position = landSquare.getPosition();
                    JLabel square = (JLabel) getComponentAt(
                            position.getX() * 50, position.getY() * 50);
                    square.setIcon(null);
                    square.setBackground(Color.GREEN);
                }
                this.repaint();
            }

            public void drawTerritory() {
                Vector<LandSquare> territory = selectedCity.getTerritory();
                Position center = selectedCity.getLocation();
                for (LandSquare landSquare : territory) {
                    Position position = landSquare.getPosition();
                    if (position.equals(center)) {
                        continue;
                    }
                    JLabel square = (JLabel) getComponentAt(
                            position.getX() * 50, position.getY() * 50);
                    square.setIcon(null);
                    square.setBackground(Color.GREEN);
                }
                this.repaint();
            }

            public void drawAttackSquares(Vector<LandSquare> squares) {
                attackSquares = squares;
                for (LandSquare landSquare : attackSquares) {
                    Position position = landSquare.getPosition();
                    JLabel square = (JLabel) getComponentAt(
                            position.getX() * 50, position.getY() * 50);
                    square.setBackground(Color.RED);
                }
                this.repaint();
            }

            private void drawMapWithoutUnits() {
                for (int i = 0; i < GameMapConfig.MAP_HEIGHT; i++) {
                    for (int j = 0; j < GameMapConfig.MAP_WIDTH; j++) {
                        JLabel square = (JLabel) getComponentAt(
                                j * 50, i * 50);
                        square.setBackground(Color.WHITE);
                        switch (map.getSquare(j, i).getTerrainType()) {
                            case HILL:
                                switch (map.getSquare(j, i).getLandformType()) {
                                    case DESERT:
                                        square.setIcon(hill_desert_icon);
                                        break;
                                    case FROZENGROUND:
                                        square.setIcon(hill_frozenground_icon);
                                        break;
                                    case FOREST:
                                        square.setIcon(hills_forest_icon);
                                        break;
                                    case GRASSLANDS:
                                        square.setIcon(hills_grasslands_icon);
                                        break;
                                    case RAINFOREST:
                                        square.setIcon(hills_rainforest_icon);
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case LAKE:
                                square.setIcon(lake_icon);
                                break;
                            case PLAIN:
                                switch (map.getSquare(j, i).getLandformType()) {
                                    case DESERT:
                                        square.setIcon(plain_desert_icon);
                                        break;
                                    case FOREST:
                                        square.setIcon(plain_forest_icon);
                                        break;
                                    case FROZENGROUND:
                                        square.setIcon(plain_frozenground_icon);
                                        break;
                                    case GRASSLANDS:
                                        square.setIcon(plain_grasslands_icon);
                                        break;
                                    case MARSH:
                                        square.setIcon(plain_marsh_icon);
                                        break;
                                    case RAINFOREST:
                                        square.setIcon(plain_rainforest_icon);
                                        break;
                                    default:
                                        break;
                                }
                                break;
                            case RIDGE:
                                square.setIcon(ridge_icon);
                                break;
                            case RIVER_COL:
                                square.setIcon(river_col_icon);
                                break;
                            case RIVER_NE:
                                square.setIcon(river_ne_icon);
                                break;
                            case RIVER_NW:
                                square.setIcon(river_nw_icon);
                                break;
                            case RIVER_ROW:
                                square.setIcon(river_row_icon);
                                break;
                            case RIVER_SE:
                                square.setIcon(river_se_icon);
                                break;
                            case RIVER_SW:
                                square.setIcon(river_sw_icon);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }

            public void updateMap() {
                drawMapWithoutUnits();
                for (City city : cities) {
                    Position position = city.getLocation();
                    JLabel square = (JLabel) getComponentAt(
                            position.getX() * 50, position.getY() * 50);
                    square.setIcon(cityIcon);
                    square.setBackground(CountryConfig.COLOR_OF_COUNTRY
                            .get(city.getCountry().getCountryName()));
                }
                for (Unit unit : units) {
                    Position position = unit.getPosition();
                    JLabel square = (JLabel) getComponentAt(
                            position.getX() * 50, position.getY() * 50);
                    switch (unit.getSubType()) {
                        case EXPLORER:
                            square.setIcon(explorer_icon);
                            break;
                        case CONSTRUCTOR:
                            square.setIcon(constructor_icon);
                            break;
                        case FOOTMAN:
                            square.setIcon(footman_icon);
                            break;
                        case ARCHER:
                            square.setIcon(archer_icon);
                            break;
                        case KNIGHT:
                            square.setIcon(knight_icon);
                            break;
                        case SPEARMAN:
                            square.setIcon(spearman_icon);
                            break;
                        case SWORDSMAN:
                            square.setIcon(swordsman_icon);
                            break;
                        case ASSASSIN:
                            square.setIcon(scout_icon);
                            break;
                        default:
                            break;
                    }
                    square.setBackground(CountryConfig.COLOR_OF_COUNTRY
                            .get(unit.getCountry().getCountryName()));
                }
                if (citySelected) {
                    drawTerritory();
                }
                if (unitAttacking) {
                    drawAttackSquares(calculateAttackSquares());
                }
                this.repaint();
            }

            private void addUnit(Unit unit, Position position) {
                JLabel square = (JLabel) getComponentAt(
                        position.getX() * 50, position.getY() * 50);
                switch (unit.getSubType()) {
                    case EXPLORER:
                        square.setIcon(explorer_icon);
                        break;
                    case CONSTRUCTOR:
                        square.setIcon(constructor_icon);
                        break;
                    case FOOTMAN:
                        square.setIcon(footman_icon);
                        break;
                    case ARCHER:
                        square.setIcon(archer_icon);
                        break;
                    case KNIGHT:
                        square.setIcon(knight_icon);
                        break;
                    case SPEARMAN:
                        square.setIcon(spearman_icon);
                        break;
                    case SWORDSMAN:
                        square.setIcon(swordsman_icon);
                        break;
                    case ASSASSIN:
                        square.setIcon(scout_icon);
                        break;
                    default:
                        break;
                }
                square.setBackground(CountryConfig.COLOR_OF_COUNTRY
                        .get(unit.getCountry().getCountryName()));
            }

            private class MapMouseEventListener implements MouseInputListener {

                private boolean moving = false;
                private int xinit = 0;
                private int yinit = 0;
                private int xcur = 0;
                private int ycur = 0;
                Timer timer;

                @Override
                public void mouseClicked(MouseEvent event) {
                    JLabel square = (JLabel) getComponentAt(event.getX(), event.getY());

                    int posx = event.getX() / 50;
                    int posy = event.getY() / 50;
                    if (!unitMoving && !unitAttacking) { // 单位没有准备移动也没有准备攻击
                        boolean found = false;
                        for (Unit u : units) {
                            if (u.getPosition().equals(new Position(posx, posy))) {
                                if (u.getCountry() == currentPlayer) {
                                    found = true;
                                    unselectCity();
                                    unselectUnit();
                                    selectUnit(u);
                                    break;
                                }
                            }
                        }
                        for (City c : cities) {
                            if (c.getLocation().equals(new Position(posx, posy))) {
                                if (c.getCountry() == currentPlayer) {
                                    found = true;
                                    unselectUnit();
                                    unselectCity();
                                    selectCity(c);
                                    break;
                                }
                            }
                        }
                        if (!found) {
                            unselectUnit();
                            unselectCity();
                        }
                    } else if (unitMoving) { // 单位移动
                        Position position = new Position(posx, posy);
                        LandSquare landSquare = map.getSquare(posx, posy);
                        if (accessableSquares.contains(landSquare)) {
                            selectedUnit.moveTo(position);
                        }
                        unselectUnit();
                    } else if (unitAttacking) {
                        boolean found = false;
                        for (Unit unit : enermy.getUnits()) {
                            if (unit.getPosition().equals(new Position(posx, posy))) {
                                if (unit.getType() == UnitType.FIGHTER) {
                                    found = true;
                                    fight((Fighter) selectedUnit, (Fighter) unit);
                                    break;
                                }
                            }
                        }
                        for (City city : enermy.getCities()) {
                            if (city.getLocation().equals(new Position(posx, posy))) {
                                found = true;
                                fight((Fighter) selectedUnit, city);
                                break;
                            }
                        }
                        if (!found) {
                            unselectCity();
                            unselectUnit();
                        }
                        unprepareAttack();
                    }
                    updateMap();
                }

                @Override
                public void mouseEntered(MouseEvent event) {
                }

                @Override
                public void mouseExited(MouseEvent event) {
                }

                @Override
                public void mousePressed(MouseEvent event) {
                    xinit = event.getX();
                    yinit = event.getY();
                    timer = new Timer(20, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            mapPanel.setLocation(
                                    xcur - xinit + mapPanel.getX(),
                                    ycur - yinit + mapPanel.getY());
                        }
                    });
                }

                @Override
                public void mouseReleased(MouseEvent event) {
                    if (moving) {
                        int x1 = event.getX();
                        int y1 = event.getY();
                        mapPanel.setLocation(
                                xcur - xinit + mapPanel.getX(),
                                ycur - yinit + mapPanel.getY());
                        moving = false;
                        timer.stop();
                    }
                }

                @Override
                public void mouseDragged(MouseEvent event) {
                    xcur = event.getX();
                    ycur = event.getY();
                    moving = true;
                    timer.start();
                }

                @Override
                public void mouseMoved(MouseEvent event) {
                }
            }
        }
    }
}
