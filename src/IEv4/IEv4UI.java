/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IEv4;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Size;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameUtils;
import static org.bytedeco.javacpp.opencv_imgproc.resize;

/**
 *
 * @author Leonardo
 */
public class IEv4UI extends javax.swing.JFrame {
    public static int _gridX, _gridY, _gridWidth, _gridHeight, _numFrames, _growthBD, _gridHalfWidth, _gridHalfHeight, _ExWidth, _ExHeigth, _numFramesSel;
    public static float _minTolerance, _minLocalTolerance;
    public static double _aspectRatio;
    public static FFmpegFrameGrabber _grabber;
    public static boolean _euclidianComp, _framesHD;
    public static Mat _mosaic, _frame;
    public static Frame _frameSelect;

    /**
    
    /**
     * Creates new form IEv4UI
     */
    public IEv4UI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        containerOptions = new javax.swing.JTabbedPane();
        selectFrame = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        ExploreVideo = new javax.swing.JSlider();
        Select = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        GridX = new javax.swing.JTextField();
        GridY = new javax.swing.JTextField();
        Width = new javax.swing.JTextField();
        Height = new javax.swing.JTextField();
        explorerBD = new javax.swing.JPanel();
        BDExplorer = new javax.swing.JSlider();
        Pixel = new javax.swing.JCheckBox();
        HD = new javax.swing.JCheckBox();
        ViewPhoto = new javax.swing.JButton();
        ContainerImage = new javax.swing.JScrollPane();
        ImagePrint = new javax.swing.JLabel();
        ContainerSelected = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        ImageSelect = new javax.swing.JLabel();
        SavePhotoMosaic = new javax.swing.JButton();
        LoadVideo = new javax.swing.JButton();
        ShowPhotoMosaic = new javax.swing.JButton();
        EuclideanDistance = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(142, 174, 189));
        jPanel1.setToolTipText("");

        containerOptions.setFocusable(false);

        selectFrame.setBackground(new java.awt.Color(48, 65, 82));
        selectFrame.setFocusable(false);

        ExploreVideo.setBackground(new java.awt.Color(48, 65, 82));
        ExploreVideo.setMaximum(200);
        ExploreVideo.setValue(100);
        ExploreVideo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ExploreVideoMouseReleased(evt);
            }
        });

        Select.setText("Seleccionar");
        Select.setOpaque(false);
        Select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectActionPerformed(evt);
            }
        });

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Select Grid Image Out:");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Grid X :");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Grid Y :");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Width :");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Height :");

        GridX.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        GridX.setText("1");
        GridX.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GridXKeyReleased(evt);
            }
        });

        GridY.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        GridY.setText("1");
        GridY.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GridYKeyReleased(evt);
            }
        });

        Width.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Width.setText("0");
        Width.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                WidthKeyReleased(evt);
            }
        });

        Height.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Height.setText("0");
        Height.setEnabled(false);

        javax.swing.GroupLayout selectFrameLayout = new javax.swing.GroupLayout(selectFrame);
        selectFrame.setLayout(selectFrameLayout);
        selectFrameLayout.setHorizontalGroup(
            selectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectFrameLayout.createSequentialGroup()
                .addGroup(selectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(selectFrameLayout.createSequentialGroup()
                        .addGroup(selectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(selectFrameLayout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addComponent(Select))
                            .addGroup(selectFrameLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel4)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(selectFrameLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(selectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ExploreVideo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator1)
                            .addGroup(selectFrameLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Height, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(selectFrameLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Width, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, selectFrameLayout.createSequentialGroup()
                                .addGroup(selectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(selectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(GridX, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(GridY, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        selectFrameLayout.setVerticalGroup(
            selectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(selectFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(selectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(GridX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(selectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(GridY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(selectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(Width, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(selectFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(selectFrameLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ExploreVideo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Select))
                    .addGroup(selectFrameLayout.createSequentialGroup()
                        .addComponent(Height, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        containerOptions.addTab("Select Frame", null, selectFrame, "");

        explorerBD.setBackground(new java.awt.Color(48, 65, 82));

        BDExplorer.setValue(0);
        BDExplorer.setEnabled(false);
        BDExplorer.setOpaque(false);

        Pixel.setForeground(new java.awt.Color(255, 255, 255));
        Pixel.setText("Pixel");
        Pixel.setEnabled(false);
        Pixel.setOpaque(false);

        HD.setForeground(new java.awt.Color(255, 255, 255));
        HD.setText("Image HD");
        HD.setEnabled(false);
        HD.setOpaque(false);

        ViewPhoto.setText("View Photo-Mosaic");
        ViewPhoto.setEnabled(false);

        javax.swing.GroupLayout explorerBDLayout = new javax.swing.GroupLayout(explorerBD);
        explorerBD.setLayout(explorerBDLayout);
        explorerBDLayout.setHorizontalGroup(
            explorerBDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(explorerBDLayout.createSequentialGroup()
                .addGroup(explorerBDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(explorerBDLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(BDExplorer, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(explorerBDLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(Pixel)
                        .addGap(40, 40, 40)
                        .addComponent(HD))
                    .addGroup(explorerBDLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(ViewPhoto)))
                .addContainerGap())
        );
        explorerBDLayout.setVerticalGroup(
            explorerBDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(explorerBDLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(explorerBDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Pixel)
                    .addComponent(HD))
                .addGap(18, 18, 18)
                .addComponent(BDExplorer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ViewPhoto)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        containerOptions.addTab("Explore BD", null, explorerBD, "");

        ImagePrint.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ContainerImage.setViewportView(ImagePrint);

        ContainerSelected.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        ImageSelect.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jScrollPane2.setViewportView(ImageSelect);

        ContainerSelected.addTab("Image Selected", jScrollPane2);

        SavePhotoMosaic.setText("Save");
        SavePhotoMosaic.setActionCommand("Sa");
        SavePhotoMosaic.setFocusable(false);
        SavePhotoMosaic.setOpaque(false);
        SavePhotoMosaic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SavePhotoMosaicActionPerformed(evt);
            }
        });

        LoadVideo.setText("Load");
        LoadVideo.setFocusable(false);
        LoadVideo.setOpaque(false);
        LoadVideo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoadVideoActionPerformed(evt);
            }
        });

        ShowPhotoMosaic.setText("Generate Photo-Mosaic");
        ShowPhotoMosaic.setEnabled(false);
        ShowPhotoMosaic.setFocusable(false);
        ShowPhotoMosaic.setOpaque(false);

        EuclideanDistance.setForeground(new java.awt.Color(255, 255, 255));
        EuclideanDistance.setText("Euclidean Distance");
        EuclideanDistance.setEnabled(false);
        EuclideanDistance.setOpaque(false);
        EuclideanDistance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EuclideanDistanceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(containerOptions)
                    .addComponent(ContainerSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(ContainerImage, javax.swing.GroupLayout.PREFERRED_SIZE, 639, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ShowPhotoMosaic)
                        .addGap(18, 18, 18)
                        .addComponent(EuclideanDistance)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LoadVideo)
                        .addGap(18, 18, 18)
                        .addComponent(SavePhotoMosaic)))
                .addGap(27, 27, 27))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ShowPhotoMosaic)
                            .addComponent(LoadVideo)
                            .addComponent(SavePhotoMosaic)
                            .addComponent(EuclideanDistance))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(containerOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(26, 26, 26)
                            .addComponent(ContainerSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(ContainerImage, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static int calculateHeight(int x){
        return  (int) (x/_aspectRatio);
    }
    
    public static double calculateAspectRatio(int x, int y){
        return (double) x/y;
    }
    
    public static Mat resize (Mat a,int x, int y){
        org.bytedeco.javacpp.opencv_imgproc.resize(a, a, new Size(x,y) );
        return a;
    }
    
    public static void display_frame(Frame a){
        ImagePrint.setIcon(new ImageIcon (Java2DFrameUtils.toBufferedImage(resize(Java2DFrameUtils.toMat(a),636,calculateHeight(636)) )));
    }
    
    public static void display_mat_select(Mat a){
        ImageSelect.setIcon(new ImageIcon (Java2DFrameUtils.toBufferedImage(resize(a,310,calculateHeight(310)) )));
    }
    
    private void LoadVideoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoadVideoActionPerformed
        JFileChooser fChooser = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
        fChooser.setFileFilter(new FileNameExtensionFilter("mp4", "mp4"));        
        fChooser.setFileFilter(new FileNameExtensionFilter("mkv", "mkv"));
        fChooser.setFileFilter(new FileNameExtensionFilter("avi", "avi"));
        if (fChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File fSelected = fChooser.getSelectedFile();
            _grabber = new FFmpegFrameGrabber(fSelected.getAbsolutePath());
            try {
                _grabber.start();
                _numFrames = _grabber.getLengthInVideoFrames()-1; 
                _numFramesSel =_numFrames/2;
                _grabber.setVideoFrameNumber(_numFramesSel);
                _frameSelect = _grabber.grab();
                _gridWidth = _grabber.getImageWidth();
                Width.setText(Integer.toString(_gridWidth));
                _gridHeight = _grabber.getImageHeight();
                Height.setText(Integer.toString(_gridHeight));
                _aspectRatio = calculateAspectRatio(_gridWidth, _gridHeight);
                ExploreVideo.setMaximum(_numFrames);
                ExploreVideo.setValue(_numFramesSel);
                display_frame(_frameSelect);
            } catch (FrameGrabber.Exception ex) {
                Logger.getLogger(IEv4UI.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_LoadVideoActionPerformed

    private void SavePhotoMosaicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SavePhotoMosaicActionPerformed
        JFileChooser fChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fChooser.setFileFilter(new FileNameExtensionFilter("bmp", "bmp"));        
        fChooser.setFileFilter(new FileNameExtensionFilter("png", "png"));
        fChooser.setFileFilter(new FileNameExtensionFilter("jpg", "jpg"));
        if( fChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
            File fSelected = fChooser.getSelectedFile();      
            String name = fSelected.getAbsolutePath();
            if(!name.endsWith(".jpg") || !name.endsWith(".bmp") || !name.endsWith(".png")){
                name = name + "." + fChooser.getFileFilter().getDescription();
            }
            //Guardar imagen
            opencv_imgcodecs.imwrite(name,_mosaic);
        }
    }//GEN-LAST:event_SavePhotoMosaicActionPerformed

    private void ExploreVideoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExploreVideoMouseReleased
        int a ;
        try {
            a= ExploreVideo.getValue();
            _numFramesSel=a;
            _grabber.setVideoFrameNumber(_numFramesSel);
            _frameSelect=_grabber.grab();
            display_frame(_frameSelect);
        } catch (FrameGrabber.Exception ex) {
            Logger.getLogger(IEv4UI.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_ExploreVideoMouseReleased

    private void GridXKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GridXKeyReleased
       String a;
       try{
            a=GridX.getText();
            _gridX=Integer.parseInt(a);
            _gridY=_gridX;
            GridY.setText(a);
       }catch(NumberFormatException e){
       }
    }//GEN-LAST:event_GridXKeyReleased

    private void GridYKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GridYKeyReleased
        String a;
        try{
             a=GridY.getText();
             _gridY=Integer.parseInt(a);
             _gridX=_gridY;
             GridX.setText(a);
        }catch(NumberFormatException e){     
        }
    }//GEN-LAST:event_GridYKeyReleased

    private void WidthKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WidthKeyReleased
        try{
            _gridWidth=Integer.parseInt(Width.getText());
            _gridHeight=calculateHeight(_gridWidth);
            Height.setText(Integer.toString(_gridHeight));
        }catch(NumberFormatException e){
        }
    }//GEN-LAST:event_WidthKeyReleased

    private void SelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectActionPerformed
        try{
            _frame=Java2DFrameUtils.toMat(_frameSelect);
            display_mat_select(_frame);
            ShowPhotoMosaic.setEnabled(true);
            EuclideanDistance.setEnabled(true);   
        }catch(Exception e){
        }
    }//GEN-LAST:event_SelectActionPerformed

    private void EuclideanDistanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EuclideanDistanceActionPerformed
        _euclidianComp = EuclideanDistance.isSelected();
    }//GEN-LAST:event_EuclideanDistanceActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IEv4UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IEv4UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IEv4UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IEv4UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new IEv4UI().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider BDExplorer;
    private javax.swing.JScrollPane ContainerImage;
    private javax.swing.JTabbedPane ContainerSelected;
    private javax.swing.JCheckBox EuclideanDistance;
    private javax.swing.JSlider ExploreVideo;
    private javax.swing.JTextField GridX;
    private javax.swing.JTextField GridY;
    private javax.swing.JCheckBox HD;
    private javax.swing.JTextField Height;
    private static javax.swing.JLabel ImagePrint;
    private static javax.swing.JLabel ImageSelect;
    private javax.swing.JButton LoadVideo;
    private javax.swing.JCheckBox Pixel;
    private javax.swing.JButton SavePhotoMosaic;
    private javax.swing.JButton Select;
    private javax.swing.JButton ShowPhotoMosaic;
    private javax.swing.JButton ViewPhoto;
    private javax.swing.JTextField Width;
    private javax.swing.JTabbedPane containerOptions;
    private javax.swing.JPanel explorerBD;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel selectFrame;
    // End of variables declaration//GEN-END:variables
}
