package com.example.mahe.hh;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;


public class HHKeyboard extends InputMethodService
        implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView kv;
    private Keyboard keyboard;

    private boolean caps = false;

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection ic = getCurrentInputConnection();
        playClick(primaryCode);
        switch(primaryCode){
            case 1 :
                ic.commitText("I want ",1);
                break;
            case 2 :
                ic.commitText("Hi! ",1);
                break;
            case 3 :
                ic.commitText("Bye! ",1);
                break;
            case 4 :
                ic.commitText("to write ",1);
                break;
            case 5 :
                ic.commitText("to sleep ",1);
                break;
            case 6 :
                ic.commitText("food ",1);
                break;
            case 7 :
                ic.commitText("water ",1);
                break;
            case 8 :
                ic.commitText("Mom ",1);
                break;
            case 9 :
                ic.commitText("Dad ",1);
                break;
            case 10 :
                ic.commitText("to play ",1);
                break;
            case 11 :
                ic.commitText("clothes ",1);
                break;
            case 12 :
                ic.commitText("to call ",1);
                break;
            case 13 :
                ic.commitText("to watch TV ",1);
                break;
            case 14 :
                ic.commitText("help, it's an emergency! ",1);
                break;
            case 15 :
                ic.commitText("Yes ",1);
                break;
            case 16 :
                ic.commitText("No ",1);
                break;
            case 17 :
                ic.commitText("to go home ",1);
                break;
            case 18 :
                ic.commitText("money ",1);
                break;
            case 19 :
                ic.commitText("to use the toilet ",1);
                break;
            case 20 :
                ic.commitText("medicine ",1);
                break;
            case 25 :
                ic.commitText(". ",1);
                break;
            case 26 :
                ic.commitText("? ",1);
                break;

            case Keyboard.KEYCODE_DELETE :
                ic.deleteSurroundingText(1, 0);
                break;
            case Keyboard.KEYCODE_SHIFT:
                caps = !caps;
                keyboard.setShifted(caps);
                kv.invalidateAllKeys();
                break;
            case Keyboard.KEYCODE_DONE:
                ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            default:
                char code = (char)primaryCode;
                if(Character.isLetter(code) && caps){
                    code = Character.toUpperCase(code);
                }
                ic.commitText(String.valueOf(code),1);
    }
    }

    @Override
    public void onPress(int primaryCode) {
    }

    @Override
    public void onRelease(int primaryCode) {
    }

    @Override
    public void onText(CharSequence text) {
    }

    @Override
    public void swipeDown() {
    }

    @Override
    public void swipeLeft() {
    }

    @Override
    public void swipeRight() {
    }

    @Override
    public void swipeUp() {
    }

    public View onCreateInputView() {
        kv = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.qwerty);
        kv.setKeyboard(keyboard);
        kv.setOnKeyboardActionListener(this);
        return kv;
    }

    private void playClick(int keyCode){
        AudioManager am = (AudioManager)getSystemService(AUDIO_SERVICE);
        switch(keyCode){
            case 32:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                break;
            case Keyboard.KEYCODE_DONE:
            case 10:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                break;
            case Keyboard.KEYCODE_DELETE:
                am.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                break;
            default: am.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        }
    }
}